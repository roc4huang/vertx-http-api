package com.spriet2000.vertx.http.api.handlers.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.Result;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import static com.spriet2000.vertx.http.api.binding.method.MethodInvoker.methodInvoker;


public class MethodHandler implements BiFunction<Consumer<Throwable>, Consumer<RouteContext>, Consumer<RouteContext>> {

    @Override
    public Consumer<RouteContext> apply(Consumer<Throwable> fail, Consumer<RouteContext> next) {
        return ctx -> {
            try {
                Value[] values = new Value[ctx.methodInfo().parameters().length];
                ctx.methodInfo().parametersBinder().bind(ctx, values);
                Result result = new Result(methodInvoker(ctx.methodInfo()).invoke(values));
                ctx.result(result);
                next.accept(ctx);
            } catch (Exception e) {
                fail.accept(e);
            }
        };
    }
}