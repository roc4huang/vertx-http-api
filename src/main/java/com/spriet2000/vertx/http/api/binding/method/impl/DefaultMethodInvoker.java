package com.spriet2000.vertx.http.api.binding.method.impl;


import com.spriet2000.vertx.http.api.binding.method.MethodInvoker;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.spriet2000.vertx.http.api.binding.value.Converter.convert;


public final class DefaultMethodInvoker implements MethodInvoker {

    @Override
    public Value invoke(RouteContext context, RouteResult routeResult) throws InvocationTargetException, IllegalAccessException {
        Method method = routeResult.methodInfo().getMethod();

        ArrayList<Value> values = new ArrayList<>();
        routeResult.methodInfo().binder().bind(context, routeResult.methodInfo(), values);

        Object[] arguments = values.stream().map(c -> convert(c.getValue(), c.getType())).toArray();
        Value result = new Value(method.invoke(routeResult.controller(), arguments), method.getReturnType());

        return result;
    }
}
