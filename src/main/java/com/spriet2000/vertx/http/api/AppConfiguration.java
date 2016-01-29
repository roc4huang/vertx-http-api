package com.spriet2000.vertx.http.api;


import com.spriet2000.vertx.http.api.controllers.Controllers;
import com.spriet2000.vertx.http.api.impl.DefaultAppConfiguration;
import io.vertx.core.Vertx;

public interface AppConfiguration {

    static AppConfiguration configure(Vertx vertx, Controllers controllers, Router router, AppHandler appHandler) {
        return new DefaultAppConfiguration(vertx, controllers, router, appHandler);
    }

    Vertx vertx();

    Controllers controllers();

    Router router();

    AppHandler appHandler();
}
