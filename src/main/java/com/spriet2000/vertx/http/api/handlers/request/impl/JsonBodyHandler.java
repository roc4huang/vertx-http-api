package com.spriet2000.vertx.http.api.handlers.request.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;
import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class JsonBodyHandler implements BiFunction<BiConsumer<RouteContext, Throwable>, BiConsumer<RouteContext, RouteResult>,
        BiConsumer<RouteContext, RouteResult>> {

    private Class clazz;

    @Override
    public BiConsumer<RouteContext, RouteResult> apply(BiConsumer<RouteContext, Throwable> fail, BiConsumer<RouteContext, RouteResult> next) {
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
                        Map<String, String> map = mapper.readValue(body.toString(), new TypeReference<Map<String, String>>(){});
                        context.data().addAll(map);
                        next.accept(context, result);
                    } catch (Exception exception) {
                        fail.accept(context, exception);
                    }
                });
            }
        };
    }
}