package com.spriet2000.vertx.http.api.binding.parameters;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameters.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

public interface ParametersBinder {

    static ParametersBinder newInstance() {
        return new DefaultParametersBinder();
    }

    void bind(RoutingContext context, MethodInfo methodInfo, Object[] arguments);
}
