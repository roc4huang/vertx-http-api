package com.spriet2000.vertx.http.api.impl;


import com.github.spriet2000.handlers.BiHandlers;
import com.spriet2000.vertx.http.api.AppHandler;
import com.spriet2000.vertx.http.api.handlers.impl.*;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;

import java.util.function.BiConsumer;

public class DefaultHandler implements AppHandler {

    private BiConsumer<RouteContext, RouteResult> consumer;

    @Override
    public void accept(RouteContext context, RouteResult result) {
        if (consumer == null) {
            BiHandlers<RouteContext, RouteResult> handlers = new BiHandlers<>(
                    new BodyHandler(),
                    new DataBinderHandler(),
                    new MethodInvokeHandler(),
                    new ResultHandler());
            consumer = handlers.apply(new ErrorHandler(), new SuccessHandler());
        }
        consumer.accept(context, result);
    }
}
