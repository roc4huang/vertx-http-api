package vertx.handlers.http.api.ext.impl;

import com.github.spriet2000.vertx.handlers.http.server.ServerController;
import com.github.spriet2000.vertx.handlers.http.server.ServerHandler;
import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.Handler;

public class ContentTypeHandler implements ServerController {

    @Override
    public ServerHandler<Object> handle(Handler fail, Handler next) {
        return (req, res, context) -> {
            res.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json");
            next.handle(context);
        };
    }
}
