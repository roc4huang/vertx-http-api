package vertx.handlers.http.api.ext.impl;

import com.github.spriet2000.vertx.handlers.http.server.ServerController;
import com.github.spriet2000.vertx.handlers.http.server.ServerHandler;
import io.vertx.core.Handler;


public class ExceptionHandler implements ServerController {

    @Override
    public ServerHandler<ErrorContext> handle(Handler fail, Handler next) {
        return (req, res, context) -> {
            res.setStatusCode(context.code());
            next.handle(context);
        };
    }
}
