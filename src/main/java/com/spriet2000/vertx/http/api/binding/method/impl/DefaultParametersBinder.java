package com.spriet2000.vertx.http.api.binding.method.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.binding.value.ValueBinder.newValueBinder;

public class DefaultParametersBinder implements ParametersBinder {

    @Factory
    public static Supplier factory() {
        return DefaultParametersBinder::new;
    }

    @Override
    public void bind(RouteContext context, MethodInfo methodInfo, Value... values) {
        ParameterInfo[] parameters = methodInfo.parameters();
        for (int i = 0; i < parameters.length; i++) {
            ValueBinder valueBinder = newValueBinder();
            values[i] = valueBinder.bind(context, methodInfo, parameters[i]);
        }
    }
}
