package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.ParameterValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;


public class DefaultParameterValue implements ParameterValue {

    @Override
    public Object get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        if(parameterInfo.getValueFromCookie()){
            return new CookieValue().get(context, methodInfo, parameterInfo);
        }else if(parameterInfo.getValueFromQuery()){
             return new QueryValue().get(context, methodInfo, parameterInfo);
        } else {
            return new ParametersValue().get(context, methodInfo, parameterInfo);
        }
    }
}
