package vertx.handlers.http.api.routing.impl;

import io.vertx.core.http.HttpMethod;
import vertx.handlers.http.api.activation.Activator;
import vertx.handlers.http.api.activation.impl.DefaultActivator;
import vertx.handlers.http.api.binding.Parameters;
import vertx.handlers.http.api.binding.impl.DefaultParameterBinder;
import vertx.handlers.http.api.controllers.impl.ControllerRegistry;
import vertx.handlers.http.api.helpers.AnnotationsHelper;
import vertx.handlers.http.api.routing.*;

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
