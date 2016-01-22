package com.spriet2000.vertx.http.api.binding.parameters.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.parameters.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.value.impl.DefaultParameterValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

import java.util.function.Supplier;

public class DefaultParametersBinder implements ParametersBinder {

    @Factory
    public static Supplier factory() {
        return DefaultParametersBinder::new;
    }

    @Override
    public void bind(RoutingContext context, MethodInfo methodInfo, Object... arguments) {
        ParameterInfo[] parameters = methodInfo.parameters();
        for (int i = 0; i < parameters.length; i++) {
            ParameterInfo parameterInfo = parameters[i];
            DefaultParameterValue parameterValue = new DefaultParameterValue();
            arguments[i] = parameterValue.get(context, methodInfo, parameterInfo);
        }
    }
}
