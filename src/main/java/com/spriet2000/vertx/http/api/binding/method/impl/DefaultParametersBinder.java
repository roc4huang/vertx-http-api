package com.spriet2000.vertx.http.api.binding.method.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.util.ArrayList;

public class DefaultParametersBinder implements ParametersBinder {

    @Override
    public void bind(RouteContext context, MethodInfo methodInfo, ArrayList<Value> values) {
        for (ParameterInfo parameter : methodInfo.parameters()) {
            values.add(new Value(context.data().get(parameter.name()),
                    parameter.parameter().getType()));
        }
    }
}
