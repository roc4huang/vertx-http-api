package com.spriet2000.vertx.http.api.helpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationsHelper {

    public static <T> T findFirstAnnotation(List<Annotation> annotations, Class<T> attributeType) {
        for (Annotation annotation : annotations) {
            if (attributeType.isAssignableFrom(annotation.annotationType())) {
                return (T) annotation;
            }
        }
        return null;
    }

    public static Method findFirstMethodWithAnnotation(Class<?> controllerType, Class<?> attributeType) {
        for (Method method : controllerType.getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (attributeType.isAssignableFrom(annotation.annotationType())) {
                    return method;
                }
            }
        }
        return null;
    }
}
