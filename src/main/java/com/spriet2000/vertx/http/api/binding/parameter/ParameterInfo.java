package com.spriet2000.vertx.http.api.binding.parameter;

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
}
