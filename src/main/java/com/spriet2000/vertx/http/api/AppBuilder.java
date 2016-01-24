package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.controllers.impl.Controllers;
import com.spriet2000.vertx.http.api.impl.DefaultAppBuilder;
import com.spriet2000.vertx.http.api.routing.impl.RouteRegistry;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

public interface AppBuilder {

    static AppBuilder builder() {
        return new DefaultAppBuilder();
    }

    RouteRegistry routes();

    AppBuilder use(Router mapping);

    AppBuilder use(Controllers registry);

    AppBuilder use(AppHandler appHandler);

    Handler<HttpServerRequest> build();

}
