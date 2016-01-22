package com.spriet2000.vertx.http.api.binding.impl;

import com.spriet2000.vertx.http.api.binding.MethodInfo;
import com.spriet2000.vertx.http.api.binding.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.ParameterValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;


public class DefaultParameterValue implements ParameterValue {

    @Override
    public Object get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        Object result;
        ParametersValue parametersValue = new ParametersValue();
        result = parametersValue.get(context, methodInfo, parameterInfo);
        if(result == null) {
            QueryValue queryValue = new QueryValue();
            result = queryValue.get(context, methodInfo, parameterInfo);
        }
        return result;
    }
}