package com.spriet2000.vertx.http.api.handlers.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.routing.impl.Result;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;


import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class JsonBodyHandler implements BiFunction<BiConsumer<RouteContext, Throwable>, BiConsumer<RouteContext, Result>,
        BiConsumer<RouteContext, Result>> {

    private Class clazz;

    @Override
    public BiConsumer<RouteContext, Result> apply(BiConsumer<RouteContext, Throwable> fail, BiConsumer<RouteContext, Result> next) {
        return (context, result) -> {
            if (context.request().headers().contains(HttpHeaders.Names.CONTENT_TYPE)
                    && !context.request().headers().get(HttpHeaders.Names.CONTENT_TYPE).equals("application/json")) {
                next.accept(context, result);
                return;
            }
            if (context.request().method() == HttpMethod.GET
                    || context.request().method() == HttpMethod.HEAD) {
                next.accept(context, result);
            } else {
                Buffer body = Buffer.buffer();
                context.request().handler(body::appendBuffer);
                context.request().endHandler(e -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        context.body(mapper.readValue(body.toString(), clazz));
                        next.accept(context, result);
                    } catch (Exception exception) {
                        fail.accept(context, exception);
                    }
                });
            }
        };
    }
}