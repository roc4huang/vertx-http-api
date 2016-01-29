package com.spriet2000.vertx.http.api.handlers.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.Result;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static com.spriet2000.vertx.http.api.binding.method.MethodInvoker.invoker;


public class MethodHandler implements BiFunction<BiConsumer<RouteContext, Throwable>,
        BiConsumer<RouteContext, Result>, BiConsumer<RouteContext, Result>> {

    @Override
    public BiConsumer<RouteContext, Result> apply(BiConsumer<RouteContext, Throwable> fail, BiConsumer<RouteContext, Result> next) {
        return (context, result) -> {
            try {
                Value[] values = new Value[context.method().parameters().length];
                context.method().binder().bind(context, values);
                result.complete(invoker(context.method()).invoke(values));
                next.accept(context, result);
            } catch (Exception e) {
                fail.accept(context, e);
            }
        };
    }
}