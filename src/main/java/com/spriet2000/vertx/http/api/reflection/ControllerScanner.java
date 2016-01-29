package com.spriet2000.vertx.http.api.reflection;


import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.impl.DefaultControllers;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

public class ControllerScanner {

    public static DefaultControllers scanClassPaths(String... classPaths) {
        DefaultControllers list = new DefaultControllers();
        new FastClasspathScanner(classPaths)
                .matchSubclassesOf(Controller.class, c -> {
                    list.add((Class<Controller>) c);
                }).scan();
        return list;
    }

}
