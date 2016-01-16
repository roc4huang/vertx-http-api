package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.helpers.AnnotationsHelper;

import java.lang.reflect.Parameter;

public final class ParameterInfo {

    private final Parameter parameter;
    private String identifier;

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
    }

    public Parameter getParameter() {
        return parameter;
    }

    public String identifier() {
        return identifier;
    }

    @Override
    public int hashCode() {
        return identifier().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return identifier().equals(object);
    }
}
