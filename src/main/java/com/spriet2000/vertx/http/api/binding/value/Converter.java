package com.spriet2000.vertx.http.api.binding.value;

public class Converter {

    public static Object convert(Object object, Class<?> clazz) {
        return com.toddfast.util.convert.TypeConverter.convert(clazz, object);
    }

}
