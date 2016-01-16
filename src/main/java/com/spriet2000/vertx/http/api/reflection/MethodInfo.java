package com.spriet2000.vertx.http.api.reflection;

import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import com.spriet2000.vertx.http.api.binding.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.helpers.AnnotationsHelper;

import java.lang.reflect.Method;

public final class MethodInfo {
    private final String identifier;
    private final Class<?> declaringClass;
    private final Method method;
    private final ParameterInfo[] parameters;
    private final ParametersBinder parametersBinder;

    public MethodInfo(Method method, ParameterInfo... parameters) {
        this.identifier = method.getName();
        this.declaringClass = method.getDeclaringClass();
        this.method = method;
        this.parameters = parameters;

        Parameters parameters1 = AnnotationsHelper.findFirstAnnotation(method.getAnnotations(), Parameters.class);

        if (parameters1 == null){
            this.parametersBinder = new DefaultParametersBinder();
        }else {
            DefaultActivator activator = new DefaultActivator(parameters1.binder());
            this.parametersBinder = (ParametersBinder) activator.newInstance();
        }


    }

    public Method getMethod() {
        return method;
    }

    public ParameterInfo[] getParameters() {
        return parameters;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    public ParametersBinder getParametersBinder(){
        return parametersBinder;
    }
}
