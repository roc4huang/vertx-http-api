package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.binding.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

public interface ParametersBinder {

    static ParametersBinder newInstance() {
        return new DefaultParametersBinder();
    }

    void bind(RoutingContext context, MethodInfo methodInfo, Object[] arguments);
}
