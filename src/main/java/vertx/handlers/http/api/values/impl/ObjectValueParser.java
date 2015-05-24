package vertx.handlers.http.api.values.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import vertx.handlers.http.api.routing.impl.ParameterInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;
import vertx.handlers.http.api.values.Parser;

public class ObjectValueParser implements Parser {

    @Override
    public void parse(ParameterInfo parameter, RoutingContext context, Future<Object> future) {
        if(context.request() == null) {
            future.fail(new Exception("Request is null"));
            return;
        }
        if (context.request().headers() == null) {
            future.fail(new Exception("Request is invalid"));
            return;
        }
        if (!context.request().headers().contains(HttpHeaders.Names.CONTENT_TYPE) &&
                !context.request().headers().get(HttpHeaders.Names.CONTENT_TYPE)
                        .equals("application/json")) {
            future.fail(new Exception("Request is invalid"));
            return;
        }
        Buffer body = Buffer.buffer();
        context.request().handler(body::appendBuffer);
        context.request().endHandler(e -> {
            try {
                future.complete(new ObjectMapper().readValue(body.toString(), parameter.type()));
            } catch (Exception exception) {
                future.fail(exception);
            }
        });
    }
}
