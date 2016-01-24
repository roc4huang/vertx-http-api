package com.spriet2000.vertx.http.api.binders.value.impl;

import com.spriet2000.vertx.http.api.binders.method.MethodInfo;
import com.spriet2000.vertx.http.api.binders.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binders.value.Value;
import com.spriet2000.vertx.http.api.binders.value.ValueBinder;
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
