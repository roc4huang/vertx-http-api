package com.spriet2000.vertx.http.api.binding.value;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

public interface ParameterValue {

    Object get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo);
}
