package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;


public class ParametersValueBinder implements ValueBinder {

    @Override
    public Value bind(RouteContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        return new Value(context.parameters().get(parameterInfo.name()),
                parameterInfo.parameter().getType());
    }
}
