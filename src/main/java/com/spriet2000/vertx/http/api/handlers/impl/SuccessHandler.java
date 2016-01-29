package com.spriet2000.vertx.http.api.handlers.impl;

import com.spriet2000.vertx.http.api.routing.impl.Result;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.function.BiConsumer;


public class SuccessHandler implements BiConsumer<RouteContext, Result> {

    static Logger logger = LoggerFactory.getLogger(SuccessHandler.class);

    @Override
    public void accept(RouteContext routeContext, Result result) {
        logger.info("success ", routeContext);
    }
}