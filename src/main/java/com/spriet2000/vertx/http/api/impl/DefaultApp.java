package com.spriet2000.vertx.http.api.impl;

import com.spriet2000.vertx.http.api.App;
import com.spriet2000.vertx.http.api.AppBuilder;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class DefaultApp implements App {
    private final Vertx vertx;
    private AppBuilder builder;
    private Handler<HttpServerRequest> handler;

    public DefaultApp(Vertx vertx) {

        this.vertx = vertx;
    }

    @Override
    public DefaultApp configure(AppBuilder builder) {
        this.builder = builder;
        return this;
    }

    @Override
    public void handle(HttpServerRequest request) {
        if (handler == null) {
            handler = handler();
        }
        handler.handle(request);
    }

    private Handler<HttpServerRequest> handler() {
        return builder.build();
    }

}
