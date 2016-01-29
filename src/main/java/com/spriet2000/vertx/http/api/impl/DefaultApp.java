package com.spriet2000.vertx.http.api.impl;

import com.spriet2000.vertx.http.api.App;
import com.spriet2000.vertx.http.api.AppBuilder;
import com.spriet2000.vertx.http.api.AppConfiguration;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.impl.RouteInfo;
import com.spriet2000.vertx.http.api.routing.impl.Routes;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.lang.reflect.Method;

import static com.spriet2000.vertx.http.api.reflection.MethodInfoHelper.toMethodInfo;
import static com.spriet2000.vertx.http.api.reflection.RouteInfoHelper.toRouteInfo;


public class DefaultApp implements App {

    static Logger logger = LoggerFactory.getLogger(DefaultApp.class);

    private final Routes routes = new Routes();

    private AppConfiguration configuration;
    private Handler<HttpServerRequest> handler;

    @Override
    public App builder(AppBuilder builder) {
        configuration = builder.build();
        return this;
    }

    @Override
    public void accept(HttpServerRequest request) {
        if (handler == null) {
            handler = handler();
        }
        handler.handle(request);
    }

    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        accept(httpServerRequest);
    }

    private Handler<HttpServerRequest> handler() {

        logger.info("Mapping controllers");

        for (Class<? extends Controller> controllerClass : configuration.controllers().list()) {
            logger.info(String.format("Found controller %s", controllerClass));
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                RouteInfo routeInfo = toRouteInfo(method);
                if (routeInfo == null) {
                    continue;
                }
                logger.info(String.format("Found route %s", routeInfo));
                MethodInfo methodInfo = toMethodInfo(method);

                logger.info(String.format("Register method %s", methodInfo.getMethod()));
                routes.put(routeInfo, methodInfo);
            }
        }

        logger.info("Mapping controllers completed");

        return configuration.router().accept(routes, configuration.appHandler());
    }
}
