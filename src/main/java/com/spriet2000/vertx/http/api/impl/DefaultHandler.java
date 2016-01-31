package com.spriet2000.vertx.http.api.impl;


import com.github.spriet2000.handlers.BiHandlers;
import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.handlers.impl.ErrorHandler;
import com.spriet2000.vertx.http.api.handlers.impl.SuccessHandler;
import com.spriet2000.vertx.http.api.handlers.request.impl.DataBinderHandler;
import com.spriet2000.vertx.http.api.handlers.request.impl.MethodInvokeHandler;
import com.spriet2000.vertx.http.api.handlers.response.impl.JsonResultHandler;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;

import java.util.function.BiConsumer;

public class DefaultHandler implements AppHandler {

    private BiConsumer<RouteContext, RouteResult> consumer;

    @Override
    public void accept(RouteContext context, RouteResult result) {
        if (consumer == null) {
            BiHandlers<RouteContext, RouteResult> handlers = new BiHandlers<>(
                    new DataBinderHandler(), new MethodInvokeHandler(), new JsonResultHandler());
            consumer = handlers.apply(new ErrorHandler(), new SuccessHandler());
        }
        consumer.accept(context, result);
    }
}
