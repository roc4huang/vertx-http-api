package com.spriet2000.vertx.http.api.binding.argument.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.argument.Argument;
import com.spriet2000.vertx.http.api.binding.value.impl.CookieValue;
import com.spriet2000.vertx.http.api.binding.value.impl.ParametersValue;
import com.spriet2000.vertx.http.api.binding.value.impl.QueryValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;


public class DefaultArgument implements Argument {

    @Override
    public Value get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        if (parameterInfo.getValueFromCookie()) {
            return new CookieValue().get(context, methodInfo, parameterInfo);
        } else if (parameterInfo.getValueFromQuery()) {
            return new QueryValue().get(context, methodInfo, parameterInfo);
        } else {
            return new ParametersValue().get(context, methodInfo, parameterInfo);
        }
    }
}
