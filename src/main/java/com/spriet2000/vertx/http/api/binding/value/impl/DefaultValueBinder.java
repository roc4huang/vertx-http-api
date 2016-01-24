package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;


public class DefaultValueBinder implements ValueBinder {

    @Override
    public Value bind(RouteContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        switch (parameterInfo.getFrom()) {
            case NotSet:
                return new ParametersValueBinder().bind(context, methodInfo, parameterInfo);
            case Query:
                return new QueryValueBinder().bind(context, methodInfo, parameterInfo);
            case Cookie:
                return new CookieValueBinder().bind(context, methodInfo, parameterInfo);
        }
        return null;
    }
}
