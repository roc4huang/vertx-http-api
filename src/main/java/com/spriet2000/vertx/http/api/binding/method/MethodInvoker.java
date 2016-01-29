package com.spriet2000.vertx.http.api.binding.method;

import com.spriet2000.vertx.http.api.binding.method.impl.DefaultMethodInvoker;
import com.spriet2000.vertx.http.api.binding.value.Value;

import java.lang.reflect.InvocationTargetException;

public interface MethodInvoker {
    static MethodInvoker methodInvoker(MethodInfo methodInfo) {
        return new DefaultMethodInvoker(methodInfo);
    }

    Value invoke(Value... parameters) throws InvocationTargetException, IllegalAccessException;
}
