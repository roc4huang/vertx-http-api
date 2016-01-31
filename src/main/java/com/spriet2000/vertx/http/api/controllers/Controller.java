package com.spriet2000.vertx.http.api.controllers;

import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public abstract class Controller {

    private RouteContext context;

    public void routeContext(RouteContext context) {

        this.context = context;
    }

    public RouteContext routeContext() {

        return context;
    }
}
