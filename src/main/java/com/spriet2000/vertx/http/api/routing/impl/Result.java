package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;

public class Result {

    private Value result;

    public Value value() {
        return result;
    }

    public void complete(Value result) {
        this.result = result;
    }
}
