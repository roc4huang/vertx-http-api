package com.spriet2000.vertx.http.api.controllers;

import com.spriet2000.vertx.http.api.controllers.impl.DefaultControllers;

import java.util.ArrayList;

public interface Controllers {


    static Controllers controllers(Class<? extends Controller> controller) {
        return new DefaultControllers(controller);
    }

    @SafeVarargs
    static Controllers controllers(Class<? extends Controller>... controllers) {
        return new DefaultControllers(controllers);
    }

    ArrayList<Class<? extends Controller>> list();
}
