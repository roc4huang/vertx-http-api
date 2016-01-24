package com.spriet2000.vertx.http.api.reflection;

import com.spriet2000.vertx.http.api.binders.parameter.From;
import com.spriet2000.vertx.http.api.binders.parameter.FromCookie;
import com.spriet2000.vertx.http.api.binders.parameter.FromQuery;
import com.spriet2000.vertx.http.api.binders.parameter.ParameterInfo;

import java.lang.reflect.Parameter;

import static com.spriet2000.vertx.http.api.reflection.AnnotationScanner.findFirstAnnotation;

public class ParameterAnalyzer {

    public static ParameterInfo toParameterInfo(Parameter parameter) {
        return new ParameterInfo(getName(parameter), findFrom(parameter), parameter);
    }

    private static String getName(Parameter parameter) {
        com.spriet2000.vertx.http.api.binders.parameter.Parameter namedParameter =
                findFirstAnnotation(parameter.getAnnotations(),
                        com.spriet2000.vertx.http.api.binders.parameter.Parameter.class);

        if (namedParameter == null) {
            return parameter.getName();
        } else {
            return namedParameter.name();
        }
    }

    private static From findFrom(Parameter parameter) {
        FromQuery query = findFirstAnnotation(parameter.getAnnotations(),
                FromQuery.class);

        if (query != null) {
            return From.Query;
        }
        FromCookie cookie = findFirstAnnotation(parameter.getAnnotations(),
                FromCookie.class);

        if (cookie != null) {
            return From.Cookie;
        }

        return From.NotSet;
    }
}
