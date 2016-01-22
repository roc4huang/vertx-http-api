package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.value.Value;

public class DefaultValue implements Value {

    private Object value;
    private Class<?> clazz;

    public DefaultValue(Object value, Class<?> clazz){

        this.value = value;
        this.clazz = clazz;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public Class<?> getType() {
        return clazz;
    }
}
