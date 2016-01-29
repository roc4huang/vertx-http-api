package com.spriet2000.vertx.http.api.reflection;


import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.Controllers;
import com.spriet2000.vertx.http.api.controllers.impl.DefaultControllers;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

public class ControllerScanner {

    public static Controllers scan(String... classPaths) {
        DefaultControllers controllers = new DefaultControllers();
        new FastClasspathScanner(classPaths)
                .matchSubclassesOf(Controller.class, c -> controllers.list().add(c)).scan();
        return controllers;
    }
}
