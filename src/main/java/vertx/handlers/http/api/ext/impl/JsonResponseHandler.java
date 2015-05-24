package vertx.handlers.http.api.ext.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.spriet2000.vertx.handlers.http.server.ServerController;
import com.github.spriet2000.vertx.handlers.http.server.ServerHandler;
import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.Handler;
import vertx.handlers.http.api.ext.Result;

public class JsonResponseHandler implements ServerController {

    @Override
    public ServerHandler<Result> handle(Handler fail, Handler next) {
        return (req, res, context) -> {
            if (!res.headers().contains(HttpHeaders.Names.CONTENT_TYPE)) {
                next.handle(context);
                return;
            }
            if (!res.headers().get(HttpHeaders.Names.CONTENT_TYPE).equals("application/json")) {
                next.handle(context);
                return;
            }
            try {
                res.end(new ObjectMapper().writeValueAsString(context.result()));
                next.handle(context);
            } catch (JsonProcessingException e) {
                fail.handle(e);
            }
        };
    }
}
