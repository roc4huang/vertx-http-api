package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.binding.impl.DefaultParameterBinder;

public interface ParameterBinder {

    static ParameterBinder newInstance() {
        return new DefaultParameterBinder();
    }

    void bind();
}
