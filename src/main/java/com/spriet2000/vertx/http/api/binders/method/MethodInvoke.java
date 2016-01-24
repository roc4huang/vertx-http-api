package com.spriet2000.vertx.http.api.binders.method;


import com.spriet2000.vertx.http.api.activation.Activator;

import java.lang.reflect.InvocationTargetException;


public final class MethodInvoke {

    private final MethodInfo methodInfo;

    public MethodInvoke(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public Object invoke(Object... parameters) throws InvocationTargetException, IllegalAccessException {
        Activator activator = methodInfo.getDeclaringClassActivator();

        return methodInfo.getMethod().invoke(activator.newInstance(), parameters);
    }


}
