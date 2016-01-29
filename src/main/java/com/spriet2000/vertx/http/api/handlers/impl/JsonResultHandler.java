package com.spriet2000.vertx.http.api.handlers.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spriet2000.vertx.http.api.routing.impl.Result;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.http.HttpHeaders;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class JsonResultHandler implements BiFunction<BiConsumer<RouteContext, Throwable>,
        BiConsumer<RouteContext, Result>, BiConsumer<RouteContext, Result>> {

    @Override
    public BiConsumer<RouteContext, Result> apply(BiConsumer<RouteContext, Throwable> fail, BiConsumer<RouteContext, Result> next) {
        return (context, result) -> {
            try {
                ObjectWriter writer = new ObjectMapper().writer();
                Object value = result.value().getValue();
                String json = writer.writeValueAsString(value);
                context.request().response().headers().add(HttpHeaders.CONTENT_LENGTH, String.valueOf(json.length()));
                context.request().response().headers().add(HttpHeaders.CONTENT_TYPE, "application/json");
                context.request().response().end(json);
                next.accept(context, result);
            } catch (JsonProcessingException e) {
                fail.accept(context, e);
            }
        };
    }
}