package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.argument.Argument;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

import static com.spriet2000.vertx.http.api.binding.value.Value.newValue;

public class ParametersValue implements Argument {

    @Override
    public Value get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        return newValue(context.parameters().get(parameterInfo.name()), parameterInfo.getClass());
    }
}
