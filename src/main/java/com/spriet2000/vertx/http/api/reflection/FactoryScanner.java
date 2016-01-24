package com.spriet2000.vertx.http.api.reflection;

import com.spriet2000.vertx.http.api.activation.Factory;

import java.lang.reflect.Method;


public class FactoryScanner {

    public static Method findFactory(Class<?> clazz) {
        return AnnotationScanner.findFirstMethodWithAnnotation(clazz, Factory.class);
    }
}
