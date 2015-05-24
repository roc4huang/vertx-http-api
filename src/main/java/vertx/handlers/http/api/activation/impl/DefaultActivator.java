package vertx.handlers.http.api.activation.impl;

import vertx.handlers.http.api.activation.Activator;
import vertx.handlers.http.api.activation.Provides;
import vertx.handlers.http.api.controllers.Controller;
import vertx.handlers.http.api.helpers.AnnotationsHelper;

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
    public Object activate() {
        if (activator != null) {
            return activator.get();
        }
        Method factory = AnnotationsHelper.findFirstMethodWithAnnotation(type, Provides.class);
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
                activator = (Supplier<Controller>) factory.invoke(null, new Object[]{});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
        }
        return activator.get();
    }

}
