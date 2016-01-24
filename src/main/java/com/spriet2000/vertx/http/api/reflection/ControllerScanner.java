package com.spriet2000.vertx.http.api.reflection;


import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.impl.Controllers;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

public class ControllerScanner {

    public static Controllers scanClassPaths(String... classPaths) {
        Controllers list = new Controllers();
        new FastClasspathScanner(classPaths)
                .matchSubclassesOf(Controller.class, c -> {
                    list.add((Class<Controller>) c);
                }).scan();
        return list;
    }

}
