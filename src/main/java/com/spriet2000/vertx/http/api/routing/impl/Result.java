package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;

public class Result {

    private Value resultValue;

    public Result(Value value) {

        this.resultValue = value;
    }

    public Value value() {
        return resultValue;
    }
}
