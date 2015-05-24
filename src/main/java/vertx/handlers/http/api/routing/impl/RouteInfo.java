package vertx.handlers.http.api.routing.impl;

import io.vertx.core.http.HttpMethod;
import vertx.handlers.http.api.routing.Route;

public class RouteInfo {
    private final Route route;
    private final HttpMethod httpMethod;

    public RouteInfo(Route route, HttpMethod httpMethod) {

        this.route = route;
        this.httpMethod = httpMethod;
    }

    public Route route() {
        return route;
    }

    public HttpMethod httpMethod() {
        return httpMethod;
    }

    @Override
    public int hashCode() {
        return route.path().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return route.path().equals(object);
    }
}
