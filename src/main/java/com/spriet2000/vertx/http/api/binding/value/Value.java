package com.spriet2000.vertx.http.api.binding.value;

import static com.spriet2000.vertx.http.api.binding.value.Converter.convert;

public final class Value {

    private Object rawValue;
    private Class<?> type;

    public Value(Object rawValue, Class<?> type) {

        this.rawValue = rawValue;
        this.type = type;
    }

    public Object getValue() {
        return convert(type, rawValue);
    }

}
