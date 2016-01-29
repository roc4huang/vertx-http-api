package com.spriet2000.vertx.http.api.impl;


import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.MethodInvoker;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.lang.reflect.InvocationTargetException;

public class DefaultHandler implements AppHandler {

    @Override
    public void handle(RouteContext context) {


        Value[] values = new Value[context.methodInfo().parameters().length];

        MethodInfo methodInfo = context.methodInfo();
        methodInfo.parametersBinder().bind(context, values);

        Object result = null;

        try {
            MethodInvoker invoker = new MethodInvoker(methodInfo);
            result = invoker.invoke(values);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        assert result != null;
        context.request().response().end(result.toString());
    }
}
