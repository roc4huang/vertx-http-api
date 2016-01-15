package vertx.handlers.http.api.binding.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import vertx.handlers.http.api.activation.Provides;
import vertx.handlers.http.api.binding.BindingContext;
import vertx.handlers.http.api.binding.ParameterBinder;
import vertx.handlers.http.api.routing.impl.ParameterInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class DefaultParameterBinder implements ParameterBinder {

    @Provides
    public static Supplier newInstance() {
        return DefaultParameterBinder::new;
    }

    @Override
    public void bind(RoutingContext context, Handler<Object[]> handler) throws InvocationTargetException, IllegalAccessException {
        BindingContext bindingContext = new DefaultBindingContext(
                context.controller().methodInfo().parameters().size());
        bindingContext.completeHandler(handler::handle);
        for (ParameterInfo parameter : context.controller().methodInfo().parameters()) {
            Future<Object> future = Future.future();
            bindingContext.add(future);
            //parameter.newValueHandlerInstance().get(parameter, context, future);
        }
    }
}
