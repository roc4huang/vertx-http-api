package com.spriet2000.vertx.http.api.controllers;

import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.Vertx;

public abstract class Controller {

    private RouteContext context;
    private Vertx vertx;

    public void routeContext(RouteContext context) {
        this.context = context;
    }

    public RouteContext context() {
        return context;
    }

    public void vertx(Vertx vertx){
        this.vertx = vertx;
    }

    public Vertx vertx() {
        return vertx;
    }
}