package com.spriet2000.vertx.http.api.binding.argument;

import com.spriet2000.vertx.http.api.binding.argument.impl.DefaultArgument;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.impl.DefaultValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

public interface Argument {

    Value get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo);


    static Argument newArgument(){
        return new DefaultArgument();
    }
}
