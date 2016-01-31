package com.spriet2000.vertx.http.api.handlers.request.impl;

import com.spriet2000.vertx.http.api.binding.data.DataBinder;
import com.spriet2000.vertx.http.api.binding.data.imp.DefaultDataBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public class DataBinderHandler implements BiFunction<BiConsumer<RouteContext, Throwable>,
        BiConsumer<RouteContext, RouteResult>, BiConsumer<RouteContext, RouteResult>> {

    DataBinder binder = new DefaultDataBinder();

    @Override
    public BiConsumer<RouteContext, RouteResult> apply(BiConsumer<RouteContext, Throwable> fail, BiConsumer<RouteContext, RouteResult> next) {
        return (context, result) -> {
            try {
                for (ParameterInfo parameterInfo : result.methodInfo().parameters()) {
                    binder.bind(context, parameterInfo);
                }
                next.accept(context, result);
            } catch (Exception e) {
                fail.accept(context, e);
            }
        };
    }
}