package com.spriet2000.vertx.http.api;


import com.spriet2000.vertx.http.api.routing.impl.RouteRegistry;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

public interface Router {

    Handler<HttpServerRequest> accept(RouteRegistry routeRegistry, AppHandler handler);
}
