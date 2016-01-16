package com.spriet2000.vertx.http.api.reflection;

import com.spriet2000.vertx.http.api.helpers.AnnotationsHelper;

import java.lang.reflect.Parameter;

public final class ParameterInfo {

    private String identifier;
    private final Parameter parameter;

    public ParameterInfo (Parameter parameter) {
        com.spriet2000.vertx.http.api.reflection.Parameter namedParameter  =
            AnnotationsHelper.findFirstAnnotation(parameter.getAnnotations(),
                    com.spriet2000.vertx.http.api.reflection.Parameter.class);

        if (namedParameter == null){
            this.identifier = parameter.getName();
        } else {
            this.identifier = namedParameter.name();
        }

        this.parameter = parameter;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public int hashCode() {
        return getIdentifier().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return getIdentifier().equals(object);
    }
}
