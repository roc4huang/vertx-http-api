package com.spriet2000.vertx.http.api.activation.impl;

import com.spriet2000.vertx.http.api.activation.Activator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.reflection.FactoryHelper.findFactory;


public class DefaultActivator implements Activator {

    private final Class<?> type;
    private Supplier activator;

    public DefaultActivator(Class<?> type) {

        this.type = type;
    }

    @Override
    public Object newInstance() {
        if (activator != null) {
            return activator.get();
        }
        Method factory = findFactory(type);
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
