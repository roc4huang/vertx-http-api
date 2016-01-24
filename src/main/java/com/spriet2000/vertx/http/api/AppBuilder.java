package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.controllers.impl.ControllerRegistry;
import com.spriet2000.vertx.http.api.routing.impl.RouteRegistry;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

public interface AppBuilder {

    RouteRegistry routes();

    AppBuilder useRouter(Router mapping);

    AppBuilder useControllers(ControllerRegistry registry);

    AppBuilder useHandler(AppHandler appHandler);

    Handler<HttpServerRequest> build();

}
