package com.spriet2000.vertx.http.api.controllers.impl;

import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import com.spriet2000.vertx.http.api.controllers.ControllerRegistryBuilder;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import com.spriet2000.vertx.http.api.activation.Activator;
import com.spriet2000.vertx.http.api.controllers.Controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerScanner implements ControllerRegistryBuilder {
    static Logger logger = LoggerFactory.getLogger(ControllerScanner.class);
    private final String[] classPaths;

    public ControllerScanner(String... classPaths) {
        this.classPaths = classPaths;
    }

    public static ControllerRegistryBuilder scanClassPaths(String... classPaths) {
        return new ControllerScanner(classPaths);
    }

    public ControllerRegistry build() {
        ControllerRegistry registry = new ControllerRegistry();
        ControllerScanner scanner = new ControllerScanner();
        scanner.scan(classPaths).forEach(type -> {
            try {
                logger.info(String.format("Register controller %s", type.getSimpleName()));
                Activator activator = new DefaultActivator(type);
                registry.get().put(activator, scanner.getMethods(type));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return registry;
    }

    private Map<Method, List<Annotation>> getMethods(Class<Controller> clazz)
            throws ClassNotFoundException {
        Map<Method, List<Annotation>> map = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            List<Annotation> annotations = new ArrayList<>();
            for (Annotation annotation : method.getAnnotations()) {
                logger.info(String.format("Register annotation %s %s %s", clazz.getSimpleName(),
                        method.getName(), annotation.annotationType().getSimpleName()));
                annotations.add(annotation);
            }
            map.put(method, annotations);
        }
        return map;
    }

    private List<Class<Controller>> scan(String... classPaths) {
        List<Class<Controller>> controllers = new ArrayList<>();
        logger.info("Scanning for controllers");
        // todo classpath
        new FastClasspathScanner(classPaths)
                .matchSubclassesOf(Controller.class, c -> {
                    logger.info(String.format("Found controller %s", c));
                    controllers.add((Class<Controller>) c);
                }).scan();
        logger.info("Scanning for controllers completed");
        return controllers;
    }

}
