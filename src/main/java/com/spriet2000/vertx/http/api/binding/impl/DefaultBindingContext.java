package com.spriet2000.vertx.http.api.binding.impl;

import com.spriet2000.vertx.http.api.binding.BindingContext;
import io.vertx.core.Future;
import io.vertx.core.Handler;

import java.util.ArrayList;
import java.util.List;

public class DefaultBindingContext implements BindingContext {

    private final List<Future<Object>> list = new ArrayList<>();

    private final List<Object> results = new ArrayList<>();
    private final int parameterCount;

    private Handler<Object[]> completeHandler;

    public DefaultBindingContext(int parameterCount){

        this.parameterCount = parameterCount;
    }

    @Override
    public void add(Future<Object> future) {
        future.setHandler(f -> {
                    results.add(f.result());
                    if (results.size() == parameterCount) {
                        completeHandler.handle(results.toArray());
                    }
        });
        list.add(future);
    }

    @Override
    public void completeHandler(Handler<Object[]> completeHandler) {
        this.completeHandler = completeHandler;
    }
}