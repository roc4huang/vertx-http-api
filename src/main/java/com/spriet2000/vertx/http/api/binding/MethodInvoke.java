package com.spriet2000.vertx.http.api.binding;


import com.spriet2000.vertx.http.api.activation.Activator;

import java.lang.reflect.InvocationTargetException;


public final class MethodInvoke {

    private final MethodInfo methodInfo;
    private final Object[] parameters;

    public MethodInvoke(MethodInfo methodInfo, Object... parameters) {
        this.methodInfo = methodInfo;
        this.parameters = parameters;
    }

    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        Activator activator = methodInfo.getDeclaringClassActivator();
        return methodInfo.getMethod().invoke(activator.newInstance(), parameters);
    }

}
