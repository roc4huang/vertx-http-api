package com.spriet2000.vertx.http.api.binding.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.*;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

import java.util.function.Supplier;

public class DefaultParametersBinder implements ParametersBinder {

    @Factory
    public static Supplier factory() {
        return DefaultParametersBinder::new;
    }

    @Override
    public void bind(RoutingContext context, MethodInfo methodInfo, Object... arguments) {
        for (int i = 0, argumentsLength = arguments.length; i < argumentsLength; i++) {
            arguments[i] = context.parameters().get(methodInfo.parameters()[i].identifier());
        }
    }
}
