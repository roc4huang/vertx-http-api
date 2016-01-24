package com.spriet2000.vertx.http.api.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationScanner {

    public static <T> T findFirstAnnotation(List<Annotation> annotations, Class<T> attributeType) {
        for (Annotation annotation : annotations) {
            if (attributeType.isAssignableFrom(annotation.annotationType())) {
                return (T) annotation;
            }
        }
        return null;
    }

    public static <T> T findFirstAnnotation(Annotation[] annotations, Class<T> attributeType) {
        for (Annotation annotation : annotations) {
            if (attributeType.isAssignableFrom(annotation.annotationType())) {
                return (T) annotation;
            }
        }
        return null;
    }

    public static Method findFirstMethodWithAnnotation(Class<?> classType, Class<?> attributeType) {
        for (Method method : classType.getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (attributeType.isAssignableFrom(annotation.annotationType())) {
                    return method;
                }
            }
        }
        return null;
    }
}
