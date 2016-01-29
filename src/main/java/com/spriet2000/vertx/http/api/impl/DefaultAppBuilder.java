package com.spriet2000.vertx.http.api.impl;

import com.spriet2000.vertx.http.api.AppBuilder;
import com.spriet2000.vertx.http.api.AppConfiguration;
import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.Router;
import com.spriet2000.vertx.http.api.controllers.Controllers;
import com.spriet2000.vertx.http.api.controllers.impl.DefaultControllers;
import io.vertx.core.Vertx;


public class DefaultAppBuilder implements AppBuilder {

    private Vertx vertx;
    private Router router;

    private Controllers controllers;
    private AppHandler appHandler;

    public DefaultAppBuilder(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public AppBuilder use(Router router) {
        this.router = router;
        return this;
    }

    @Override
    public AppBuilder use(Controllers controllers) {
        this.controllers = controllers;
        return this;
    }

    @Override
    public AppBuilder use(AppHandler appHandler) {
        this.appHandler = appHandler;
        return this;
    }

    @Override
    public AppConfiguration build() {
        if (controllers == null) {
            controllers = new DefaultControllers();
        }
        if (router == null) {
            router = new DefaultRouter();
        }
        if (appHandler == null) {
            appHandler = new DefaultHandler();
        }
        return new DefaultAppConfiguration(vertx, controllers, router, appHandler);
    }
}
