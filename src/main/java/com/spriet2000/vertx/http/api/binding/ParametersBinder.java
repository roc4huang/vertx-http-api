package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.binding.impl.DefaultParametersBinder;

public interface ParametersBinder {

    static ParametersBinder newInstance() {
        return new DefaultParametersBinder();
    }

    void bind();
}
