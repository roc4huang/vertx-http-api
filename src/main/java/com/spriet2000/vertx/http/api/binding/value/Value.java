package com.spriet2000.vertx.http.api.binding.value;

public final class Value {

    private Object rawValue;
    private Class<?> type;

    public Value(Object rawValue, Class<?> type) {

        this.rawValue = rawValue;
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return rawValue;
    }
}
