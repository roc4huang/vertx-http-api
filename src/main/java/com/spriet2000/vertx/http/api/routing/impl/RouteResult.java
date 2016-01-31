package com.spriet2000.vertx.http.api.routing.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.controllers.Controller;

import java.lang.reflect.InvocationTargetException;

public class RouteResult {

    private Value result;
    private MethodInfo method;
    private Controller instance;

    public RouteResult(MethodInfo method) {
        this.method = method;
    }

    public Value value() {
        return result;
    }

    public void complete(Value result) {
        this.result = result;
    }

    public MethodInfo methodInfo() {
        return method;
    }

    public Controller controller() {
        if (instance == null) {
            try {
                instance = (Controller) method.getActivator().newInstance();
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
