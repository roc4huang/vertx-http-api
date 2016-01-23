package com.spriet2000.vertx.http.api.binding.value;

public class Converter {

    public static Object convert(Class<?> clazz, Object object){
        return com.toddfast.util.convert.TypeConverter.convert(clazz, object);
    }

}
