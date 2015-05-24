package vertx.handlers.http.api.routing.impl;

import vertx.handlers.http.api.activation.Activator;
import vertx.handlers.http.api.controllers.Controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class MethodInfo {

    private final Method method;
    private final Activator parameterBinderActivator;
    private final List<ParameterInfo> parameters = new ArrayList<>();

    public MethodInfo(Activator parameterBinderActivator, Method method) {

        this.method = method;
        this.parameterBinderActivator = parameterBinderActivator;
        for (Parameter parameter : method.getParameters()) {
            parameters.add(new ParameterInfo(parameter.getName(), parameter));
        }
    }

    public Object invoke(Controller controller, Object[] parameters) throws InvocationTargetException, IllegalAccessException {
        return method().invoke(controller, parameters);
    }

    public Method method() {
        return method;
    }

    public List<ParameterInfo> parameters() {
        return parameters;
    }

    public Activator parameterBinderActivator() {
        return parameterBinderActivator;
    }
}
