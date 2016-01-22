package com.spriet2000.vertx.http.api.binding.parameter;

import java.lang.reflect.Parameter;

import static com.spriet2000.vertx.http.api.helpers.AnnotationsHelper.findFirstAnnotation;

public final class ParameterInfo {

    private final Parameter parameter;
    private final String name;
    private final boolean fromQuery;
    private final boolean fromCookie;

    public ParameterInfo(Parameter parameter) {
        this.parameter = parameter;
        this.name = getName();
        this.fromQuery = getFromQuery();
        this.fromCookie = !this.fromQuery && getFromCookie();
    }

    public Parameter parameter() {
        return parameter;
    }

    public String name() {
        return name;
    }

    public boolean getValueFromQuery(){
        return fromQuery;
    }

    public boolean getValueFromCookie(){
        return fromCookie;
    }

    @Override
    public int hashCode() {
        return name().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return name().equals(object);
    }

    private String getName(){
        com.spriet2000.vertx.http.api.binding.parameter.Parameter namedParameter =
                findFirstAnnotation(parameter.getAnnotations(),
                        com.spriet2000.vertx.http.api.binding.parameter.Parameter.class);

        if (namedParameter == null) {
            return parameter.getName();
        } else {
            return namedParameter.name();
        }
    }

    private boolean getFromQuery(){
        FromQuery namedParameter = findFirstAnnotation(parameter.getAnnotations(),
                        FromQuery.class);

        if (namedParameter == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean getFromCookie(){
        FromCookie namedParameter = findFirstAnnotation(parameter.getAnnotations(),
                FromCookie.class);

        if (namedParameter == null) {
            return false;
        } else {
            return true;
        }
    }
}
