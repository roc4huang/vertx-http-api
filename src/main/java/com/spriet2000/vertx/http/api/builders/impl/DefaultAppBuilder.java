package com.spriet2000.vertx.http.api.builders.impl;

import com.github.spriet2000.vertx.httprouter.Router;
import com.spriet2000.vertx.http.api.builders.AppBuilder;
import com.spriet2000.vertx.http.api.controllers.ControllerRegistryBuilder;
import com.spriet2000.vertx.http.api.controllers.impl.ControllerRegistry;
import com.spriet2000.vertx.http.api.routing.impl.RouteRegistry;
import com.spriet2000.vertx.http.api.routing.impl.RouteScanner;

public class DefaultAppBuilder implements AppBuilder {
    private ControllerRegistry controllerRegistry;
    private Router router;

    public DefaultAppBuilder useRouter(Router router) {
        this.router = router;
        return this;
    }

    public DefaultAppBuilder useControllers(ControllerRegistryBuilder builder) {
        controllerRegistry = builder.build();
        return this;
    }

    @Override
    public Router router() {
        return router;
    }

    @Override
    public RouteRegistry routes() {
        RouteRegistry routeRegistry = new RouteRegistry();
        RouteScanner.buildRoutes(controllerRegistry, routeRegistry);
        return routeRegistry;
    }

}
