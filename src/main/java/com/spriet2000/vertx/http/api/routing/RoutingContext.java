package com.spriet2000.vertx.http.api.routing;


import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;

public final class RoutingContext {

    private final HttpServerRequest httpServerRequest;
    private final MultiMap parameters;

    public RoutingContext(HttpServerRequest httpServerRequest){

        this.httpServerRequest = httpServerRequest;
        this.parameters = MultiMap.caseInsensitiveMultiMap();
    }

    public HttpServerRequest getHttpServerRequest() {
        return httpServerRequest;
    }

    public MultiMap parameters() {
        return parameters;
    }
}
