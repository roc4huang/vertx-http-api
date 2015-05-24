package vertx.handlers.http.api.values.impl;


import io.vertx.core.Future;
import vertx.handlers.http.api.routing.impl.ParameterInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;
import vertx.handlers.http.api.values.Parser;

public class SimpleValueParser implements Parser {

    public void parse(ParameterInfo parameter, RoutingContext context, Future<Object> future) {
        String result = context.parameters().get(":" + parameter.name());
        if (result == null && context.parameters().containsKey(parameter.name())) {
            result = context.parameters().get(parameter.name());
        }
        if (result == null && context.request().params().contains(parameter.name())) {
            result = context.request().params().get(parameter.name());
        }
        if (result == null && context.request().isExpectMultipart()) {
            result = context.request().formAttributes().get(parameter.name());
        }
        future.complete(result);
    }
}
