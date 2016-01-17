package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.binding.MethodInfo;

import java.util.HashMap;

public class RouteRegistry {

    HashMap<RouteInfo, MethodInfo> registry;

    public RouteRegistry() {
        registry = new HashMap<>();
    }

    public HashMap<RouteInfo, MethodInfo> get() {
        return registry;
    }
}
