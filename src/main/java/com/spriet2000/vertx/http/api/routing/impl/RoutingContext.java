package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.binding.MethodInfo;
import io.vertx.core.http.HttpServerRequest;

import java.util.Map;

public class RoutingContext {
    private final RouteInfo routeInfo;
    private final MethodInfo controllerInfo;
    private Map<String, String> parameters;
    private HttpServerRequest request;

    public RoutingContext(RouteInfo key, MethodInfo value, Map<String, String> parameters, HttpServerRequest request) {

        this.routeInfo = key;
        this.controllerInfo = value;
        this.parameters = parameters;
        this.request = request;
    }

    public HttpServerRequest request() {
        return request;
    }

    public Map<String, String> parameters() {
        return parameters;
    }

    public MethodInfo controller() {
        return controllerInfo;
    }
}
