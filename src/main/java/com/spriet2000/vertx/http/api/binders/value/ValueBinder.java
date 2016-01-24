package com.spriet2000.vertx.http.api.binders.value;

import com.spriet2000.vertx.http.api.binders.method.MethodInfo;
import com.spriet2000.vertx.http.api.binders.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binders.value.impl.DefaultValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public interface ValueBinder {

    static ValueBinder newValueBinder() {
        return new DefaultValueBinder();
    }

    Value bind(RouteContext context, MethodInfo methodInfo, ParameterInfo parameterInfo);
}
