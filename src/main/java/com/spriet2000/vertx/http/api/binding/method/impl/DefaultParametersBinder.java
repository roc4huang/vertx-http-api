package com.spriet2000.vertx.http.api.binding.method.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.binding.value.ValueBinder.valueBinder;

public class DefaultParametersBinder implements ParametersBinder {

    private final ValueBinder binder = valueBinder();

    @Factory
    public static Supplier factory() {
        return DefaultParametersBinder::new;
    }

    @Override
    public void bind(RouteContext context, Value... values) {
        ParameterInfo[] parameters = context.method().parameters();
        for (int i = 0; i < parameters.length; i++) {
            values[i] = binder.bind(context, context.method(), parameters[i]);
        }
    }
}
