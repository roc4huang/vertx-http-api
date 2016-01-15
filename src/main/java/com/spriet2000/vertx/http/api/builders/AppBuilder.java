package com.spriet2000.vertx.http.api.builders;

import com.github.spriet2000.vertx.httprouter.Router;
import com.spriet2000.vertx.http.api.routing.impl.RouteRegistry;

public interface AppBuilder {

    Router router();

    RouteRegistry routes();

}
