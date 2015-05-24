package vertx.handlers.http.api.test.helper;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import vertx.handlers.http.api.activation.Activator;
import vertx.handlers.http.api.activation.impl.DefaultActivator;
import vertx.handlers.http.api.binding.impl.DefaultParameterBinder;
import vertx.handlers.http.api.routing.impl.ControllerInfo;
import vertx.handlers.http.api.routing.impl.MethodInfo;
import vertx.handlers.http.api.routing.impl.RouteInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RoutingContextBuilder {
    private HttpServerRequest request;
    private Map<String, String> parameters;
    private RouteInfo routeKey;
    private ControllerInfo routeValue;


    public static RoutingContextBuilder createRoutingContext(){
        return new RoutingContextBuilder();
    }

    public RoutingContextBuilder() {

    }

    public RoutingContextBuilder(TestHttpServerRequest request) {

        this.request = request;
    }

    public RoutingContextBuilder with(String path, HttpMethod httpMethod) {
        routeKey = new RouteInfo(new TestRoute(path, path), httpMethod);
        return this;
    }

    public RoutingContextBuilder with(String name, String path, HttpMethod httpMethod) {
        routeKey = new RouteInfo(new TestRoute(name, path), httpMethod);
        return this;
    }

    public RoutingContextBuilder with(Class controller, Method controllerMethod) {
        Activator controllerActivator = new DefaultActivator(controller);
        Activator parameterBinderActivator = new DefaultActivator(DefaultParameterBinder.class);
        MethodInfo method = new MethodInfo(parameterBinderActivator, controllerMethod);
        routeValue = new ControllerInfo(controllerActivator, method);
        return this;
    }

    public RoutingContextBuilder with(Map<String, String> parameters) {

        this.parameters = parameters;
        return this;
    }

    public RoutingContext build() {
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        return new RoutingContext(routeKey, routeValue, parameters, request);
    }
}
