package com.spriet2000.vertx.http.api.binders.method;

import com.spriet2000.vertx.http.api.binders.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binders.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public interface ParametersBinder {

    static ParametersBinder newInstance() {
        return new DefaultParametersBinder();
    }

    void bind(RouteContext context, MethodInfo methodInfo, Value[] arguments);
}
