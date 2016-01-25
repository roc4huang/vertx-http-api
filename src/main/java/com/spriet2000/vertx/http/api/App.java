package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.controllers.impl.Controllers;
import com.spriet2000.vertx.http.api.impl.DefaultApp;
import com.spriet2000.vertx.http.api.routing.impl.Routes;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

import java.util.function.Consumer;

public interface App extends Handler<HttpServerRequest> {

    static App webApp(Vertx vertx) {
        return new DefaultApp(vertx);
    }

    Vertx vertx();

    Controllers controllers();

    App configure(Consumer<App> handler);

    Routes routes();

    void handle(HttpServerRequest request);

    App use(Router mapping);

    App use(Controllers registry);

    App use(AppHandler appHandler);
}
