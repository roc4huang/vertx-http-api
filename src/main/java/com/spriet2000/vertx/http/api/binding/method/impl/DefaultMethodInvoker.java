package com.spriet2000.vertx.http.api.binding.method.impl;


import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.MethodInvoker;
import com.spriet2000.vertx.http.api.binding.value.Value;

import java.lang.reflect.InvocationTargetException;


public final class DefaultMethodInvoker implements MethodInvoker {

    private final MethodInfo methodInfo;

    public DefaultMethodInvoker(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    @Override
    public Object invoke(Value... parameters) throws InvocationTargetException, IllegalAccessException {
        Object[] arguments = new Object[parameters.length];
        for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
            arguments[i] = parameters[i].getValue();
        }
        return methodInfo.getMethod().invoke(methodInfo.getActivator().newInstance(), arguments);
    }


}
