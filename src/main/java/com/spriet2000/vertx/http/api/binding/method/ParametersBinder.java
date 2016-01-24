package com.spriet2000.vertx.http.api.binding.method;

import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public interface ParametersBinder {

    static ParametersBinder newInstance() {
        return new DefaultParametersBinder();
    }

    void bind(RouteContext context, MethodInfo methodInfo, Value[] arguments);
}
