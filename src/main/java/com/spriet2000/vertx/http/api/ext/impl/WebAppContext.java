package com.spriet2000.vertx.http.api.ext.impl;

import com.spriet2000.vertx.http.api.ext.Model;
import com.spriet2000.vertx.http.api.ext.Result;
import com.spriet2000.vertx.http.api.routing.impl.RoutingContext;

public class WebAppContext implements Model, Result {
    private final RoutingContext routingContext;

    private Object result;
    private Object model;

    public WebAppContext(RoutingContext routingContext) {
        this.routingContext = routingContext;
    }

    public Object result() {
        return result;
    }

    public void result(Object result) {
        this.result = result;
    }

    public RoutingContext routingContext() {
        return routingContext;
    }

    @Override
    public Object model() {
        return model;
    }

    public void model(Object model) {
        this.model = model;
    }
}
