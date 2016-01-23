package com.spriet2000.vertx.http.api.binding.convert;


import com.spriet2000.vertx.http.api.binding.value.Value;
import com.toddfast.util.convert.TypeConverter;

public class Converter {

    public static Object convert(Class<?> clazz, Value value){
        return TypeConverter.convert(clazz, value.getValue());
    }

}
