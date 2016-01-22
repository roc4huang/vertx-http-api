package com.spriet2000.vertx.http.api.binding.value;


import com.spriet2000.vertx.http.api.binding.value.impl.DefaultValue;

public interface Value {

    Object getValue();

    Class<?> getType();

    static Value newValue(Object object, Class<?> clazz){
        return new DefaultValue(object, clazz);
    }
}

