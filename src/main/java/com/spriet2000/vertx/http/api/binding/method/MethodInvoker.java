package com.spriet2000.vertx.http.api.binding.method;


import com.spriet2000.vertx.http.api.activation.Activator;
import com.spriet2000.vertx.http.api.binding.value.Value;

import java.lang.reflect.InvocationTargetException;


public final class MethodInvoker {

    private final MethodInfo methodInfo;

    public MethodInvoker(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public Object invoke(Value... parameters) throws InvocationTargetException, IllegalAccessException {
        Activator activator = methodInfo.getDeclaringClassActivator();

        Object[] arguments = new Object[parameters.length];
        for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
            arguments[i] = parameters[i].getValue();
        }

        return methodInfo.getMethod().invoke(activator.newInstance(), arguments);
    }


}
