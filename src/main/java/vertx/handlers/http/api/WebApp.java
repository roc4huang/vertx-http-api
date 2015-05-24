package vertx.handlers.http.api;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import vertx.handlers.http.api.builders.AppBuilder;
import vertx.handlers.http.api.impl.DefaultWebApp;

public interface WebApp extends Handler<HttpServerRequest> {

    static WebApp webApp(Vertx vertx) {
        return new DefaultWebApp(vertx);
    }

    WebApp configure(AppBuilder builder);

    void handle(HttpServerRequest request);
}
