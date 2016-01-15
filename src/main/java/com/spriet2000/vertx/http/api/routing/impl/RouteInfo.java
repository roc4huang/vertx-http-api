package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.routing.Route;
import io.vertx.core.http.HttpMethod;

public class RouteInfo {
    private final Route route;
    private final HttpMethod httpMethod;

    public RouteInfo(Route route, HttpMethod httpMethod) {

        this.route = route;
        this.httpMethod = httpMethod;
    }

    public Route route() {
        return route;
    }

    public HttpMethod httpMethod() {
        return httpMethod;
    }

    @Override
    public int hashCode() {
        return route.path().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return route.path().equals(object);
    }
}
