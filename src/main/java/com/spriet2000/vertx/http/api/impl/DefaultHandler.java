package com.spriet2000.vertx.http.api.impl;


import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public class DefaultHandler implements AppHandler {

    @Override
    public void handle(RouteContext context) {
        context.request().response().end();
    }
}
