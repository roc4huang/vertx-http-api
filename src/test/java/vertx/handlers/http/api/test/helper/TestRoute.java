package vertx.handlers.http.api.test.helper;


import vertx.handlers.http.api.routing.Route;

import java.lang.annotation.Annotation;

public class TestRoute implements Route {

    private final String name;
    private final String path;

    public TestRoute(String name, String path) {

        this.name = name;
        this.path = path;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Route.class;
    }
}
