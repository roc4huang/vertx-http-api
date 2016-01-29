package com.spriet2000.vertx.http.api.handlers.impl;

import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.function.Consumer;


public class SuccessHandler implements Consumer<RouteContext> {

    static Logger logger = LoggerFactory.getLogger(SuccessHandler.class);

    @Override
    public void accept(RouteContext routeContext) {
        logger.info("success ", routeContext);
    }
}