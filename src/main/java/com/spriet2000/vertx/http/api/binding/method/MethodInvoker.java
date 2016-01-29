package com.spriet2000.vertx.http.api.binding.method;

import com.spriet2000.vertx.http.api.binding.value.Value;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by spriet4000 on 29/01/16.
 */
public interface MethodInvoker {
    Object invoke(Value... parameters) throws InvocationTargetException, IllegalAccessException;
}
