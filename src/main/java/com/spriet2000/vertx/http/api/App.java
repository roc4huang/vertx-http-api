package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.impl.DefaultApp;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

public interface App extends Handler<HttpServerRequest> {

    static App create(AppBuilder builder) {
        return new DefaultApp().builder(builder);
    }

    App builder(AppBuilder builder);

    void accept(HttpServerRequest request);
}
