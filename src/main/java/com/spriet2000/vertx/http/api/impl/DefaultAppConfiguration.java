package com.spriet2000.vertx.http.api.impl;

import com.spriet2000.vertx.http.api.AppConfiguration;
import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.Router;
import com.spriet2000.vertx.http.api.controllers.Controllers;
import io.vertx.core.Vertx;

public class DefaultAppConfiguration implements AppConfiguration {

    private Vertx vertx;
    private final Controllers controllers;
    private final Router router;
    private final AppHandler appHandler;

    public DefaultAppConfiguration(Vertx vertx, Controllers controllers, Router router, AppHandler appHandler) {
        this.vertx = vertx;
        this.controllers = controllers;
        this.router = router;
        this.appHandler = appHandler;
    }

    @Override
    public Vertx vertx() {
        return vertx;
    }

    @Override
    public Controllers controllers() {
        return controllers;
    }

    @Override
    public Router router() {
        return router;
    }

    @Override
    public AppHandler appHandler() {
        return appHandler;
    }
}
