package vertx.handlers.http.api.binding;

import io.vertx.core.Handler;
import vertx.handlers.http.api.binding.impl.DefaultParameterBinder;
import vertx.handlers.http.api.routing.impl.RoutingContext;

import java.lang.reflect.InvocationTargetException;

public interface ParameterBinder {

    static ParameterBinder createParameterBinder(){
        return new DefaultParameterBinder();
    }

    void bind(RoutingContext routingContext, Handler<Object[]> handler) throws InvocationTargetException, IllegalAccessException;
}
