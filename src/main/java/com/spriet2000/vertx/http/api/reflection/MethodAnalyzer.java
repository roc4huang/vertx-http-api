package com.spriet2000.vertx.http.api.reflection;

import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.Parameters;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.routing.*;
import com.spriet2000.vertx.http.api.routing.impl.RouteInfo;
import io.vertx.core.http.HttpMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static com.spriet2000.vertx.http.api.reflection.AnnotationScanner.findFirstAnnotation;
import static com.spriet2000.vertx.http.api.reflection.ParameterAnalyzer.toParameterInfo;
import static com.spriet2000.vertx.http.api.reflection.RouteScanner.findRoute;


public class MethodAnalyzer {

    public static MethodInfo toMethodInfo(Method method) {
        ParameterInfo[] parameters = new ParameterInfo[method.getParameters().length];
        Parameter[] parameters1 = method.getParameters();
        for (int i = 0; i < parameters1.length; i++) {
            parameters[i] = toParameterInfo(parameters1[i]);
        }
        ParametersBinder parametersBinder;
        Parameters annotation = findFirstAnnotation(method.getAnnotations(), Parameters.class);
        if (annotation == null) {
            parametersBinder = new DefaultParametersBinder();
        } else {
            DefaultActivator activator = new DefaultActivator(annotation.binder());
            parametersBinder = (ParametersBinder) activator.newInstance();
        }
        return new MethodInfo(method.getName(), method, parametersBinder, parameters);
    }

    public static RouteInfo toRouteInfo(Method method) {
        Annotation[] annotations = method.getAnnotations();
        Route route = findRoute(annotations);
        if (route == null) {
            return null;
        }
        HttpMethod httpMethod = findHttpMethod(annotations);

        return route.name().isEmpty()
                ? new RouteInfo(getName(method), route, httpMethod)
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

    private static String getName(Method method) {
        return String.format("%s_%s", method.getDeclaringClass().getName(), method.getName());
    }
}
