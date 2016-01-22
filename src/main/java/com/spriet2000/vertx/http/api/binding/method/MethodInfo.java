package com.spriet2000.vertx.http.api.binding.method;

import com.spriet2000.vertx.http.api.activation.Activator;
import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.parameters.Parameters;
import com.spriet2000.vertx.http.api.binding.parameters.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameters.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.helpers.AnnotationsHelper;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public final class MethodInfo {
    private final String name;
    private final Method method;
    private final ParameterInfo[] parameters;
    private final ParametersBinder parametersBinder;

    public MethodInfo(Method method) {
        this.name = method.getName();
        this.method = method;
        this.parameters = new ParameterInfo[method.getParameters().length];

        Parameter[] parameters1 = method.getParameters();
        for (int i = 0; i < parameters1.length; i++) {
            parameters[i] = new ParameterInfo(parameters1[i]);
        }

        Parameters annotation = AnnotationsHelper.findFirstAnnotation(method.getAnnotations(), Parameters.class);
        if (annotation == null) {
            this.parametersBinder = new DefaultParametersBinder();
        } else {
            DefaultActivator activator = new DefaultActivator(annotation.binder());
            this.parametersBinder = (ParametersBinder) activator.newInstance();
        }
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
