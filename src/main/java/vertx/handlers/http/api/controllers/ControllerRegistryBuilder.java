package vertx.handlers.http.api.controllers;


import vertx.handlers.http.api.controllers.impl.ControllerRegistry;

public interface ControllerRegistryBuilder {
    ControllerRegistry build();
}
