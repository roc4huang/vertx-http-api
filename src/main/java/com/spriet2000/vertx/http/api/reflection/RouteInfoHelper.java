package com.spriet2000.vertx.http.api.reflection;

import com.spriet2000.vertx.http.api.routing.*;
import com.spriet2000.vertx.http.api.routing.impl.RouteInfo;
import io.vertx.core.http.HttpMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class RouteInfoHelper {

    public static Route findRoute(Annotation[] annotations) {
        return AnnotationHelper.findFirstAnnotation(annotations, Route.class);
    }

    public static RouteInfo toRouteInfo(Method method) {
        Annotation[] annotations = method.getAnnotations();
        Route route = findRoute(annotations);
        if (route == null) {
            return null;
        }
        HttpMethod httpMethod = findHttpMethod(annotations);

        return route.name().isEmpty()
                ? new RouteInfo(MethodInfoHelper.buildMethodName(method), route, httpMethod)
                : new RouteInfo(route.name(), route, httpMethod);
    }

    private static HttpMethod findHttpMethod(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isInstance(Post.class)) {
                return HttpMethod.POST;
            } else if (annotation.annotationType().isInstance(Put.class)) {
                return HttpMethod.PUT;
            } else if (annotation.annotationType().isInstance(Delete.class)) {
                return HttpMethod.DELETE;
            } else if (annotation.annotationType().isInstance(Head.class)) {
                return HttpMethod.HEAD;
            } else if (annotation.annotationType().isInstance(Patch.class)) {
                return HttpMethod.PATCH;
            }
        }
        return HttpMethod.GET;
    }
}
