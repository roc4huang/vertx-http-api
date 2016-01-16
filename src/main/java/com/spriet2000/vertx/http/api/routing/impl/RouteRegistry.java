package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.reflection.ActionInfo;

import java.util.HashMap;

public class RouteRegistry {

    HashMap<RouteInfo, ActionInfo> registry;

    public RouteRegistry() {
        registry = new HashMap<>();
    }

    public HashMap<RouteInfo, ActionInfo> get() {
        return registry;
    }
}
