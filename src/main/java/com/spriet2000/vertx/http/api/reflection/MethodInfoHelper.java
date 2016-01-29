package com.spriet2000.vertx.http.api.reflection;


import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.Parameters;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static com.spriet2000.vertx.http.api.reflection.AnnotationHelper.findFirstAnnotation;
import static com.spriet2000.vertx.http.api.reflection.ParameterInfoHelper.toParameterInfo;

public class MethodInfoHelper {

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

    public static String buildMethodName(Method method) {
        return String.format("%s_%s", method.getDeclaringClass().getName(), method.getName());
    }
}
