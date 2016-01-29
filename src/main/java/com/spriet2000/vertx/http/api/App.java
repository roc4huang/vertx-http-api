package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.impl.DefaultApp;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

public interface App extends Handler<HttpServerRequest> {

    static App create(AppBuilder builder) {
        return new DefaultApp().configure(builder);
    }

    App configure(AppBuilder builder);

    void accept(HttpServerRequest request);
}
