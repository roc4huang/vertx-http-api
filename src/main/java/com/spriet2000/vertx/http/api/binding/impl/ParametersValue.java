package com.spriet2000.vertx.http.api.binding.impl;

import com.spriet2000.vertx.http.api.binding.MethodInfo;
import com.spriet2000.vertx.http.api.binding.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.ParameterValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

public class ParametersValue implements ParameterValue {

    @Override
    public Object get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        return context.parameters().get(parameterInfo.name());
    }
}
