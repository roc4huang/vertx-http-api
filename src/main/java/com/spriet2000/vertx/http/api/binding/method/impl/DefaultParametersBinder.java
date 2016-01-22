package com.spriet2000.vertx.http.api.binding.method.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.argument.Argument;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.argument.impl.DefaultArgument;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.binding.argument.Argument.newArgument;

public class DefaultParametersBinder implements ParametersBinder {

    @Factory
    public static Supplier factory() {
        return DefaultParametersBinder::new;
    }

    @Override
    public void bind(RoutingContext context, MethodInfo methodInfo, Value... values) {
        ParameterInfo[] parameters = methodInfo.parameters();
        for (int i = 0; i < parameters.length; i++) {
            ParameterInfo parameterInfo = parameters[i];
            Argument argument = newArgument();
            values[i] = argument.get(context, methodInfo, parameterInfo);
        }
    }
}
