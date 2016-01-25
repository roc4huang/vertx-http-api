package com.spriet2000.vertx.http.api.impl;

import com.spriet2000.vertx.http.api.App;
import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.Router;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.impl.Controllers;
import com.spriet2000.vertx.http.api.routing.impl.RouteInfo;
import com.spriet2000.vertx.http.api.routing.impl.Routes;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.lang.reflect.Method;
import java.util.function.Consumer;

import static com.spriet2000.vertx.http.api.reflection.MethodAnalyzer.toMethodInfo;
import static com.spriet2000.vertx.http.api.reflection.MethodAnalyzer.toRouteInfo;

public class DefaultApp implements App {

    static Logger logger = LoggerFactory.getLogger(DefaultApp.class);

    private final Vertx vertx;
    private Handler<HttpServerRequest> handler;
    private Router router;
    private Routes routes = new Routes();
    private Controllers controllers;
    private AppHandler appHandler;

    public DefaultApp(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public Vertx vertx() {
        return vertx;
    }

    @Override
    public Routes routes() {
        return routes;
    }

    @Override
    public Controllers controllers() {
        return controllers;
    }

    @Override
    public App configure(Consumer<App> handler) {
        handler.accept(this);
        return this;
    }

    @Override
    public App use(Router router) {
        this.router = router;
        return this;
    }

    @Override
    public App use(Controllers registry) {
        this.controllers = registry;
        return this;
    }

    @Override
    public App use(AppHandler appHandler) {
        this.appHandler = appHandler;
        return this;
    }

    @Override
    public void handle(HttpServerRequest request) {
        if (handler == null) {
            handler = handler();
        }
        handler.handle(request);
    }

    private Handler<HttpServerRequest> handler() {

        logger.info("Scanning for controllers");

        for (Class<Controller> controllerClass : controllers) {
            logger.info(String.format("Found controller %s", controllerClass));
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                RouteInfo routeInfo = toRouteInfo(method);
                if (routeInfo == null) {
                    continue;
                }
                logger.info(String.format("Found accept %s", routeInfo));
                MethodInfo methodInfo = toMethodInfo(method);
                routes.put(routeInfo, methodInfo);
            }
        }

        logger.info("Scanning for controllers completed");

        if (router == null) {
            router = new DefaultRouter();
        }

        if (appHandler == null) {
            appHandler = new DefaultHandler();
        }

        return router.accept(routes, appHandler);
    }

}
