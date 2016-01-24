package com.spriet2000.vertx.http.api.binders.method.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binders.method.MethodInfo;
import com.spriet2000.vertx.http.api.binders.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binders.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binders.value.Value;
import com.spriet2000.vertx.http.api.binders.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.binders.value.ValueBinder.newValueBinder;

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
