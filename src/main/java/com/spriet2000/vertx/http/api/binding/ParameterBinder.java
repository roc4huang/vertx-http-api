package com.spriet2000.vertx.http.api.binding;

import io.vertx.core.Handler;
import com.spriet2000.vertx.http.api.binding.impl.DefaultParameterBinder;
import com.spriet2000.vertx.http.api.routing.impl.RoutingContext;

import java.lang.reflect.InvocationTargetException;

public interface ParameterBinder {

    static ParameterBinder createParameterBinder(){
        return new DefaultParameterBinder();
    }

    void bind(RoutingContext routingContext, Handler<Object[]> handler) throws InvocationTargetException, IllegalAccessException;
}
