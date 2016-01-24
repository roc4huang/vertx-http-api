package com.spriet2000.vertx.http.api.binders.value;

import static com.spriet2000.vertx.http.api.binders.value.Converter.convert;

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
