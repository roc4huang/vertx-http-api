package com.spriet2000.vertx.http.api.binding.method;

import com.spriet2000.vertx.http.api.activation.Activator;
import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;

import java.lang.reflect.Method;

public final class MethodInfo {
    private final String name;
    private final Method method;
    private final ParameterInfo[] parameters;
    private final ParametersBinder parametersBinder;

    public MethodInfo(String name, Method method, ParametersBinder parametersBinder, ParameterInfo[] parameters) {
        this.name = name;
        this.method = method;
        this.parametersBinder = parametersBinder;
        this.parameters = parameters;
    }

    public Method getMethod() {
        return method;
    }

    public ParameterInfo[] parameters() {
        return parameters;
    }

    public String name() {
        return name;
    }

    public Activator getDeclaringClassActivator() {
        return new DefaultActivator(method.getDeclaringClass());
    }

    public ParametersBinder parametersBinder() {
        return parametersBinder;
    }
}
