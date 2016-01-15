package com.spriet2000.vertx.http.api.activation.impl;

import com.spriet2000.vertx.http.api.activation.Activator;
import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.helpers.AnnotationsHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;


public class DefaultActivator implements Activator {

    private final Class<?> type;
    private Supplier activator;

    public DefaultActivator(Class<?> type) {

        this.type = type;
    }

    @Override
    public Object create() {
        if (activator != null) {
            return activator.get();
        }
        Method factory = AnnotationsHelper.findFirstMethodWithAnnotation(type, Factory.class);
        if (factory == null) {
            this.activator = () -> {
                try {
                    return type.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException
                        | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException();
                }
            };
        } else {
            try {
                activator = (Supplier) factory.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
        }
        return activator.get();
    }

}
