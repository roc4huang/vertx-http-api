package com.spriet2000.vertx.http.api.handlers.impl;

import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteResult;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.function.BiConsumer;


public class SuccessHandler implements BiConsumer<RouteContext, RouteResult> {

    static Logger logger = LoggerFactory.getLogger(SuccessHandler.class);

    @Override
    public void accept(RouteContext routeContext, RouteResult result) {
        logger.info("success ", routeContext);
    }
}