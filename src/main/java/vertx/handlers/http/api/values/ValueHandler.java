package vertx.handlers.http.api.values;

import io.vertx.core.Future;
import vertx.handlers.http.api.routing.impl.ParameterInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;

public interface ValueHandler {
    void get(ParameterInfo parameter, RoutingContext routingContext, Future<Object> handler);
}
