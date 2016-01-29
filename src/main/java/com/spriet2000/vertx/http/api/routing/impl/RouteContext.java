package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import io.vertx.core.http.HttpServerRequest;

import java.util.Map;

public class RouteContext {
    private final RouteInfo routeInfo;
    private final MethodInfo methodInfo;
    private final Map<String, String> parameters;
    private HttpServerRequest request;
    private Object body;

    public RouteContext(RouteInfo routeInfo, MethodInfo value, HttpServerRequest request, Map<String, String> parameters) {
        this.routeInfo = routeInfo;
        this.methodInfo = value;
        this.request = request;
        this.parameters = parameters;
    }

    public RouteInfo routeInfo() {
        return routeInfo;
    }

    public MethodInfo method() {
        return methodInfo;
    }

    public HttpServerRequest request() {
        return request;
    }

    public Map<String, String> parameters() {
        return parameters;
    }

    public RouteContext body(Object body) {
        this.body = body;
        return this;
    }

    public Object body() {
        return body;
    }
}
