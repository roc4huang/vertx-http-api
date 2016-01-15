package com.spriet2000.vertx.http.api.routing.impl;

import java.util.HashMap;

public class RouteRegistry {

    HashMap<RouteInfo, ControllerInfo> registry;

    public RouteRegistry() {
        registry = new HashMap<>();
    }

    public HashMap<RouteInfo, ControllerInfo> get() {
        return registry;
    }
}
