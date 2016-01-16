package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.activation.Activator;

public final class ActionInfo {
    private final Activator activator;
    private final MethodInfo methodInfo;

    public ActionInfo(Activator controllerActivator, MethodInfo method) {
        this.activator = controllerActivator;
        this.methodInfo = method;
    }

}
