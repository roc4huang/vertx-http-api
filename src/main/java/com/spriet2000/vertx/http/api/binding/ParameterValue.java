package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.routing.RoutingContext;

public interface ParameterValue {

    Object get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo);
}
