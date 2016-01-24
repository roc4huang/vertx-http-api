package com.spriet2000.vertx.http.api.binders.parameter;

import java.lang.reflect.Parameter;

public final class ParameterInfo {

    private final Parameter parameter;
    private final String name;
    private final From from;

    public ParameterInfo(String name, From from, Parameter parameter) {
        this.parameter = parameter;
        this.name = name;
        this.from = from;
    }

    public Parameter parameter() {
        return parameter;
    }

    public String name() {
        return name;
    }

    public From getFrom() {
        return from;
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
