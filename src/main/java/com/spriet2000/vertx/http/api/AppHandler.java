package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;

import java.util.function.BiConsumer;

public interface AppHandler extends BiConsumer<RouteContext, RouteResult> {
}
