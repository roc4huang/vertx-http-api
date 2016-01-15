package com.spriet2000.vertx.http.api.controllers.impl;

import com.spriet2000.vertx.http.api.activation.Activator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerRegistry {

    HashMap<Activator, Map<Method, List<Annotation>>> registry;

    public ControllerRegistry() {
        registry = new HashMap<>();
    }

    public HashMap<Activator, Map<Method, List<Annotation>>> get() {
        return registry;
    }
}
