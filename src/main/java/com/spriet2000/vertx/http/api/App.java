package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.impl.DefaultApp;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public interface App extends Handler<HttpServerRequest> {

    static App webApp(Vertx vertx) {
        return new DefaultApp(vertx);
    }

    App configure(AppBuilder builder);

    void handle(HttpServerRequest request);
}
