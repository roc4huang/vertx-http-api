package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.RoutingContext;


public class DefaultValueBinder implements ValueBinder {

    @Override
    public Value bind(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        if (parameterInfo.getValueFromCookie()) {
            return new CookieValueBinder().bind(context, methodInfo, parameterInfo);
        } else if (parameterInfo.getValueFromQuery()) {
            return new QueryValueBinder().bind(context, methodInfo, parameterInfo);
        } else {
            return new ParametersValueBinder().bind(context, methodInfo, parameterInfo);
        }
    }
}
