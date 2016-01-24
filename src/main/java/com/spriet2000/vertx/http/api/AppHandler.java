package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.Handler;

public interface AppHandler extends Handler<RouteContext> {
}
