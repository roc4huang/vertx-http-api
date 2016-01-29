package com.spriet2000.vertx.http.api.binding.method;

import com.spriet2000.vertx.http.api.binding.method.impl.DefaultMethodInvoker;
import com.spriet2000.vertx.http.api.binding.value.Value;

import java.lang.reflect.InvocationTargetException;

public interface MethodInvoker {
    static MethodInvoker invoker(MethodInfo methodInfo) {
        return new DefaultMethodInvoker(methodInfo);
    }

    Value invoke(Value... values) throws InvocationTargetException, IllegalAccessException;
}
