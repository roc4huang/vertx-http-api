package com.spriet2000.vertx.http.api.binding.impl;

import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.ParameterBinder;

import java.util.function.Supplier;

public class DefaultParameterBinder implements ParameterBinder {

    @Factory
    public static Supplier factory() {
        return DefaultParameterBinder::new;
    }

    @Override
    public void bind() {

    }
}
