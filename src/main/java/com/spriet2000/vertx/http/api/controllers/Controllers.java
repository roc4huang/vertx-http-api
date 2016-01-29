package com.spriet2000.vertx.http.api.controllers;

import com.spriet2000.vertx.http.api.controllers.impl.DefaultControllers;

import java.util.ArrayList;

public interface Controllers {

    static Controllers controllers() {
        return new DefaultControllers();
    }

    ArrayList<Class<? extends Controller>> list();

    DefaultControllers add(Class<? extends Controller> controller);
}
