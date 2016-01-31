package com.spriet2000.vertx.http.api.binding.method;

import com.spriet2000.vertx.http.api.binding.method.impl.DefaultMethodInvoker;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;

import java.lang.reflect.InvocationTargetException;

public interface MethodInvoker {
    static MethodInvoker invoker() {
        return new DefaultMethodInvoker();
    }

    Value invoke(RouteContext context, RouteResult routeResult) throws InvocationTargetException, IllegalAccessException;
}
