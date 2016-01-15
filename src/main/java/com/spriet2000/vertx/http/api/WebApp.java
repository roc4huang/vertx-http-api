package com.spriet2000.vertx.http.api;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import com.spriet2000.vertx.http.api.builders.AppBuilder;
import com.spriet2000.vertx.http.api.impl.DefaultWebApp;

public interface WebApp extends Handler<HttpServerRequest> {

    static WebApp webApp(Vertx vertx) {
        return new DefaultWebApp(vertx);
    }

    WebApp configure(AppBuilder builder);

    void handle(HttpServerRequest request);
}
