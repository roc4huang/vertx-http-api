package com.spriet2000.vertx.http.api.binding.data;

import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public interface DataBinder {

    void bind(RouteContext context, ParameterInfo parameterInfo);
}
