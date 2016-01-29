package com.spriet2000.vertx.http.api.binding.value;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.impl.DefaultValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public interface ValueBinder {

    static ValueBinder valueBinder() {
        return new DefaultValueBinder();
    }

    Value bind(RouteContext context, MethodInfo methodInfo, ParameterInfo parameterInfo);
}
