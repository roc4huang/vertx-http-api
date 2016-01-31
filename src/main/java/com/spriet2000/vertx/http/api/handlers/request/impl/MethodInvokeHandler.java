package com.spriet2000.vertx.http.api.handlers.request.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static com.spriet2000.vertx.http.api.binding.method.MethodInvoker.invoker;


public class MethodInvokeHandler implements BiFunction<BiConsumer<RouteContext, Throwable>,
        BiConsumer<RouteContext, RouteResult>, BiConsumer<RouteContext, RouteResult>> {

    @Override
    public BiConsumer<RouteContext, RouteResult> apply(BiConsumer<RouteContext, Throwable> fail, BiConsumer<RouteContext, RouteResult> next) {
        return (context, result) -> {
            try {
                Value invokeResult = invoker().invoke(context, result);
                result.complete(invokeResult);
                next.accept(context, result);
            } catch (Exception e) {
                fail.accept(context, e);
            }
        };
    }
}