package com.spriet2000.vertx.http.api.binding.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.MethodInfo;
import com.spriet2000.vertx.http.api.binding.ParametersBinder;
import io.vertx.core.http.HttpServerRequest;

import java.util.function.Supplier;

public class DefaultParametersBinder implements ParametersBinder {

    @Factory
    public static Supplier factory() {
        return DefaultParametersBinder::new;
    }

    @Override
    public void bind(HttpServerRequest request, MethodInfo methodInfo) {


    }


}