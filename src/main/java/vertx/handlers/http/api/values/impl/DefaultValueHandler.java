package vertx.handlers.http.api.values.impl;

import io.vertx.core.Future;
import vertx.handlers.http.api.activation.Provides;
import vertx.handlers.http.api.routing.impl.ParameterInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;
import vertx.handlers.http.api.values.ValueHandler;

import java.util.function.Supplier;

public class DefaultValueHandler implements ValueHandler {

    @Provides
    public static Supplier newInstance() {
        return DefaultValueHandler::new;
    }

    @Override
    public void get(ParameterInfo info, RoutingContext context, Future<Object> result) {

        // from query
        // from form-attributes
        // json, xml etc.. (codec?)
        // file upload
        // streaming body
        new SimpleValueParser().parse(info, context, result);
        // new ObjectValueParser().parse(info, context, result);
    }
}
