package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.controllers.Controllers;
import com.spriet2000.vertx.http.api.impl.DefaultAppBuilder;
import io.vertx.core.Vertx;

public interface AppBuilder {

    static AppBuilder builder(Vertx vertx) {
        return new DefaultAppBuilder(vertx);
    }

    AppBuilder use(Router mapping);

    AppBuilder use(Controllers registry);

    AppBuilder use(AppHandler appHandler);

    AppConfiguration build();

}