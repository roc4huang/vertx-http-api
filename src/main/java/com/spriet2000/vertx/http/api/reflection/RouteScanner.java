package com.spriet2000.vertx.http.api.reflection;

import com.spriet2000.vertx.http.api.routing.Route;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class RouteScanner {

    public static Route findRoute(Annotation[] annotations) {
        return AnnotationScanner.findFirstAnnotation(annotations, Route.class);
    }

    public static Route findRoute(Method method) {
        return AnnotationScanner.findFirstAnnotation(method.getAnnotations(), Route.class);
    }
}
