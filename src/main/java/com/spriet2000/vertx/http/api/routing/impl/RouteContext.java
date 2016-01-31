package com.spriet2000.vertx.http.api.routing.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;

import static io.vertx.core.MultiMap.caseInsensitiveMultiMap;

public class RouteContext {
    private final RouteInfo routeInfo;
    private final MultiMap data;
    private HttpServerRequest request;

    public RouteContext(RouteInfo routeInfo, HttpServerRequest request) {
        this.data = caseInsensitiveMultiMap();
        this.routeInfo = routeInfo;
        this.request = request;
    }

    public RouteInfo routeInfo() {
        return routeInfo;
    }

    public HttpServerRequest request() {
        return request;
    }

    public MultiMap data() {
        return data;
    }
}
