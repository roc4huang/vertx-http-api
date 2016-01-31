package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.routing.Route;
import io.vertx.core.http.HttpMethod;

public class RouteInfo {
    private String name;
    private final String path;
    private final HttpMethod httpMethod;

    public RouteInfo(String name, String path, HttpMethod httpMethod) {
        this.name = name;
        this.path = path;
        this.httpMethod = httpMethod;
    }

    public String path() {
        return path;
    }

    public HttpMethod httpMethod() {
        return httpMethod;
    }

    public String name() {
        return name;
    }
}
