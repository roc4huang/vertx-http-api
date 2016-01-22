package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.ParameterValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

public class ParametersValue implements ParameterValue {

    @Override
    public Object get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        return context.parameters().get(parameterInfo.name());
    }
}
