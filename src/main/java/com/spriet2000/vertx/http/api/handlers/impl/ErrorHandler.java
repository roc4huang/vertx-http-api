package com.spriet2000.vertx.http.api.handlers.impl;

import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.function.BiConsumer;


public class ErrorHandler implements BiConsumer<RouteContext, Throwable> {

    static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public void accept(RouteContext context, Throwable throwable) {
        logger.error("error ", throwable);
        context.request().response().setStatusCode(500);
        context.request().response().end(String.valueOf(throwable));
    }
}