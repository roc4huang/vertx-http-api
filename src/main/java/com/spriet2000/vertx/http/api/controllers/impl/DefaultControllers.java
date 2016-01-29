package com.spriet2000.vertx.http.api.controllers.impl;

import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.Controllers;

import java.util.ArrayList;

public class DefaultControllers implements Controllers {

    private ArrayList<Class<? extends Controller>> controllers = new ArrayList<>();

    @Override
    public ArrayList<Class<? extends Controller>> list() {
        return controllers;
    }

    @Override
    public DefaultControllers add(Class<? extends Controller> controller) {
        controllers.add(controller);
        return this;
    }
}
