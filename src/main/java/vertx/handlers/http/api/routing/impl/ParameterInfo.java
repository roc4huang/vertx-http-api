package vertx.handlers.http.api.routing.impl;


import vertx.handlers.http.api.activation.Activator;

import java.lang.reflect.Parameter;

public class ParameterInfo {
    private final Parameter parameter;
    private final Class<?> type;
    private Activator valueProviderActivator;
    private String name;

    public ParameterInfo(String name, Parameter parameter) {
        this.name = name;
        this.parameter = parameter;
        type = parameter.getType();
    }

    public Parameter parameter() {
        return parameter;
    }

    public Class<?> type() {
        return type;
    }

    public String name() {
        return name;
    }

    @Override
    public int hashCode() {
        return name().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return name().equals(object);
    }
}
