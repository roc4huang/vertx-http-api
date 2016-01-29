package com.spriet2000.vertx.http.api.handlers.impl;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.function.Consumer;


public class ErrorHandler implements Consumer<Throwable> {

    static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public void accept(Throwable throwable) {
        logger.error("error ", throwable);
    }
}