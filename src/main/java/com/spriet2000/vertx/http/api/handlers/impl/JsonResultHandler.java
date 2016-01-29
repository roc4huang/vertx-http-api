package com.spriet2000.vertx.http.api.handlers.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.http.HttpHeaders;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class JsonResultHandler implements BiFunction<Consumer<Throwable>, Consumer<RouteContext>, Consumer<RouteContext>> {

    @Override
    public Consumer<RouteContext> apply(Consumer<Throwable> fail, Consumer<RouteContext> next) {
        return ctx -> {
            ObjectWriter writer = new ObjectMapper().writer();
            try {
                Object value = ctx.result().value().getValue();
                String json = writer.writeValueAsString(value);
                ctx.request().response().headers().add(HttpHeaders.CONTENT_LENGTH, String.valueOf(json.length()));
                ctx.request().response().headers().add(HttpHeaders.CONTENT_TYPE, "application/json");
                ctx.request().response().end(json);
                next.accept(ctx);
            } catch (JsonProcessingException e) {
                fail.accept(e);
            }
        };
    }
}