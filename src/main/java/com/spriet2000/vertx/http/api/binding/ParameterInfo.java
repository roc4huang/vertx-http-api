package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.binding.impl.DefaultParameterValue;
import com.spriet2000.vertx.http.api.helpers.AnnotationsHelper;

import java.lang.reflect.Parameter;

public final class ParameterInfo {

    private final Parameter parameter;
    private final ParameterValue parameterValue;
    private final String identifier;

    public ParameterInfo(Parameter parameter) {
        com.spriet2000.vertx.http.api.binding.Parameter namedParameter =
                AnnotationsHelper.findFirstAnnotation(parameter.getAnnotations(),
                        com.spriet2000.vertx.http.api.binding.Parameter.class);

        if (namedParameter == null) {
            this.identifier = parameter.getName();
        } else {
            this.identifier = namedParameter.name();
        }

        this.parameter = parameter;
        this.parameterValue = new DefaultParameterValue();
    }

    public Parameter parameter() {
        return parameter;
    }

    public String name() {
        return identifier;
    }

    public ParameterValue parameterValue() {
        return parameterValue;
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
