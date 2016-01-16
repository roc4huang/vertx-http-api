package com.spriet2000.vertx.http.api.impl;

import com.spriet2000.vertx.http.api.WebApp;
import com.spriet2000.vertx.http.api.builders.AppBuilder;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class DefaultWebApp implements WebApp {
    private final Vertx vertx;
    private AppBuilder builder;
    private Handler<HttpServerRequest> handler;

    public DefaultWebApp(Vertx vertx) {

        this.vertx = vertx;
    }

    @Override
    public DefaultWebApp configure(AppBuilder builder) {
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
        /*
        final ServerHandlers handlers;
        if (builder.requestHandlers() == null) {
            handlers = defaultHandlers();
        } else {
            handlers = builder.requestHandlers();
        }

        // todo
        builder.router().notFoundHandler((ServerHandlers) handlers((fail, next) -> (req, res, args)
                -> fail.handle(new ErrorContext(404))).exceptionHandler(handlers.exceptionHandler()));

        builder.routes().get().forEach((routeInfo, controllerInfo) -> {
            RouteHandler handler = (req, parameters) ->
                    handlers.handle(req, new WebAppContext(new RoutingContext(routeInfo, controllerInfo, parameters, req)));
            switch (routeInfo.httpMethod()) {
                case GET:
                    builder.router().get(routeInfo.route().path(), handler);
                    break;
                case HEAD:
                    builder.router().head(routeInfo.route().path(), handler);
                    break;
                case POST:
                    builder.router().post(routeInfo.route().path(), handler);
                    break;
                case PUT:
                    builder.router().put(routeInfo.route().path(), handler);
                    break;
                case DELETE:
                    builder.router().delete(routeInfo.route().path(), handler);
                    break;
                case PATCH:
                    builder.router().patch(routeInfo.route().path(), handler);
                    break;
            }
        });
        */
        return builder.router();
    }

}
