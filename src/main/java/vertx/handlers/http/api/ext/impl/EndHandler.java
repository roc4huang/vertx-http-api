package vertx.handlers.http.api.ext.impl;

import com.github.spriet2000.vertx.handlers.http.server.ServerController;
import com.github.spriet2000.vertx.handlers.http.server.ServerHandler;
import io.vertx.core.Handler;

public class EndHandler implements ServerController {
    @Override
    public ServerHandler<Object> handle(Handler fail, Handler next) {
        return (req, res, context) -> {
            if (!res.ended()) {
                res.end();
            }
            next.handle(context);
        };
    }
}
