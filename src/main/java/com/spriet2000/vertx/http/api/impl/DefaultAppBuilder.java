package com.spriet2000.vertx.http.api.impl;

import com.spriet2000.vertx.http.api.AppBuilder;
import com.spriet2000.vertx.http.api.Router;
import com.spriet2000.vertx.http.api.binders.method.MethodInfo;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.impl.ControllerRegistry;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteRegistry;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.lang.reflect.Method;

import static com.spriet2000.vertx.http.api.reflection.MethodAnalyzer.toMethodInfo;
import static com.spriet2000.vertx.http.api.reflection.MethodAnalyzer.toRouteInfo;

public class DefaultAppBuilder implements AppBuilder {

    static Logger logger = LoggerFactory.getLogger(DefaultAppBuilder.class);

    private Router mapping;
    private RouteRegistry routeRegistry;
    private ControllerRegistry controllerRegistry;

    @Override
    public RouteRegistry routes() {
        return routeRegistry;
    }

    @Override
    public AppBuilder useRouter(Router mapping) {
        this.mapping = mapping;
        return this;
    }

    public AppBuilder useControllers(ControllerRegistry registry) {

        this.controllerRegistry = registry;
        return this;
    }

    @Override
    public Handler<HttpServerRequest> build(Handler<RouteContext> handler) {

        RouteRegistry routeRegistry = new RouteRegistry();

        logger.info("Scanning for controllers");

        for (Class<Controller> controllerClass : controllerRegistry) {
            logger.info(String.format("Found controller %s", controllerClass));
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                RouteInfo routeInfo = toRouteInfo(method);
                if (routeInfo == null) {
                    continue;
                }
                logger.info(String.format("Found route %s", routeInfo));
                MethodInfo methodInfo = toMethodInfo(method);
                routeRegistry.put(routeInfo, methodInfo);
            }
        }

        logger.info("Scanning for controllers completed");

        return mapping.map(routeRegistry, handler);
    }
}
