package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.controllers.impl.ControllerRegistry;
import com.spriet2000.vertx.http.api.routing.*;
import io.vertx.core.http.HttpMethod;
import com.spriet2000.vertx.http.api.activation.Activator;
import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import com.spriet2000.vertx.http.api.binding.Parameters;
import com.spriet2000.vertx.http.api.binding.impl.DefaultParameterBinder;
import com.spriet2000.vertx.http.api.helpers.AnnotationsHelper;

import java.lang.annotation.Annotation;
import java.util.List;

public class RouteScanner {

    public static void buildRoutes(ControllerRegistry registry, RouteRegistry routeRegistry) {
        registry.get().forEach((activator, controllerMethod) ->
                controllerMethod.forEach((method, annotations) -> {
                    Route route = AnnotationsHelper.findFirstAnnotation(annotations, Route.class);
                    if (route == null) {
                        return;
                    }
                    Activator binderActivator = null;
                    Parameters parameterBinding = AnnotationsHelper.findFirstAnnotation(annotations, Parameters.class);
                    binderActivator = parameterBinding == null
                            ? new DefaultActivator(DefaultParameterBinder.class)
                            : new DefaultActivator(parameterBinding.binder());
                    routeRegistry.get().put(new RouteInfo(route, findHttpMethod(annotations)),
                            new ControllerInfo(activator, new MethodInfo(binderActivator, method)));
                }));
    }

    public static HttpMethod findHttpMethod(List<Annotation> annotations) {
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
