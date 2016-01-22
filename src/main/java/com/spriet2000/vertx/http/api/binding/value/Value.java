package com.spriet2000.vertx.http.api.binding.value;

public final class Value {

    private Object value;
    private Class<?> clazz;

    public Value(Object value, Class<?> clazz) {

        this.value = value;
        this.clazz = clazz;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getType() {
        return clazz;
    }
}
