package com.spriet2000.vertx.http.api.impl;


import com.github.spriet2000.handlers.BiHandlers;
import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.handlers.impl.ErrorHandler;
import com.spriet2000.vertx.http.api.handlers.impl.JsonResultHandler;
import com.spriet2000.vertx.http.api.handlers.impl.MethodHandler;
import com.spriet2000.vertx.http.api.handlers.impl.SuccessHandler;
import com.spriet2000.vertx.http.api.routing.impl.Result;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.util.function.BiConsumer;

public class DefaultHandler implements AppHandler {

    private BiConsumer<RouteContext, Result> consumer;

    @Override
    public void handle(RouteContext context) {
        if (consumer == null) {
            BiHandlers<RouteContext, Result> handlers = new BiHandlers<>(
                    new MethodHandler(), new JsonResultHandler());
            consumer = handlers.apply(new ErrorHandler(), new SuccessHandler());
        }
        consumer.accept(context, new Result());
    }
}
