package com.spriet2000.vertx.http.api.binding.method.impl;


import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.MethodInvoker;
import com.spriet2000.vertx.http.api.binding.value.Value;

import java.lang.reflect.InvocationTargetException;

import static com.spriet2000.vertx.http.api.binding.value.Converter.convert;


public final class DefaultMethodInvoker implements MethodInvoker {

    private final MethodInfo methodInfo;

    public DefaultMethodInvoker(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    @Override
    public Value invoke(Value... parameters) throws InvocationTargetException, IllegalAccessException {
        Object[] arguments = new Object[parameters.length];
        for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
            arguments[i] = convert(parameters[i].getValue(), parameters[i].getType());
        }
        return new Value(methodInfo.getMethod().invoke(methodInfo.getActivator().newInstance(), arguments),
                methodInfo.getMethod().getReturnType());
    }

}
