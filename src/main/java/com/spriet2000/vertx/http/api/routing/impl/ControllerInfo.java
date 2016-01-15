package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.activation.Activator;

public class ControllerInfo {
    private final Activator controllerActivator;
    private final MethodInfo methodInfo;

    public ControllerInfo(Activator controllerActivator, MethodInfo method) {
        this.controllerActivator = controllerActivator;
        this.methodInfo = method;
    }

    public MethodInfo methodInfo() {
        return methodInfo;
    }

    public Activator activator() {
        return controllerActivator;
    }
}
