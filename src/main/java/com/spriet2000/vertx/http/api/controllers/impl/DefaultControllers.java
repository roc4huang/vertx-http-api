package com.spriet2000.vertx.http.api.controllers.impl;

import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.Controllers;

import java.util.ArrayList;
import java.util.Collections;

public class DefaultControllers implements Controllers {

    private ArrayList<Class<? extends Controller>> controllers = new ArrayList<>();

    @SafeVarargs
    public DefaultControllers(Class<? extends Controller>... controllers){
        Collections.addAll(this.controllers, controllers);
    }

    @Override
    public ArrayList<Class<? extends Controller>> list() {
        return controllers;
    }

}
