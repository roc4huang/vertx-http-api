package vertx.handlers.http.api.impl;

import com.github.spriet2000.vertx.handlers.http.server.ServerHandlers;
import com.github.spriet2000.vertx.handlers.http.server.ext.impl.ResponseTimeHandler;
import com.github.spriet2000.vertx.handlers.http.server.ext.impl.TimeOutHandler;
import com.github.spriet2000.vertx.httprouter.RouteHandler;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import vertx.handlers.http.api.WebApp;
import vertx.handlers.http.api.builders.AppBuilder;
import vertx.handlers.http.api.ext.impl.*;
import vertx.handlers.http.api.routing.impl.RoutingContext;

import static com.github.spriet2000.vertx.handlers.http.server.ServerHandlers.handlers;

public class DefaultWebApp implements WebApp {
    private final Vertx vertx;
    private AppBuilder builder;
    private Handler<HttpServerRequest> handler;

    public DefaultWebApp(Vertx vertx) {

        this.vertx = vertx;
    }

    @Override
    public DefaultWebApp configure(AppBuilder builder) {
        this.builder = builder;
        return this;
    }

    @Override
    public void handle(HttpServerRequest request) {
        if (handler == null) {
            handler = handler();
        }
        handler.handle(request);
    }

    private Handler<HttpServerRequest> handler() {
        final ServerHandlers handlers;
        if (builder.requestHandlers() == null) {
            handlers = defaultHandlers();
        } else {
            handlers = builder.requestHandlers();
        }

        // todo
        builder.router().notFoundHandler((ServerHandlers) handlers((fail, next) -> (req, res, args)
                -> fail.handle(new ErrorContext(404))).exceptionHandler(handlers.exceptionHandler()));

        builder.routes().get().forEach((routeInfo, controllerInfo) -> {
            RouteHandler handler = (req, parameters) ->
                    handlers.handle(req, new WebAppContext(new RoutingContext(routeInfo, controllerInfo, parameters, req)));
            switch (routeInfo.httpMethod()) {
                case GET:
                    builder.router().get(routeInfo.route().path(), handler);
                    break;
                case HEAD:
                    builder.router().head(routeInfo.route().path(), handler);
                    break;
                case POST:
                    builder.router().post(routeInfo.route().path(), handler);
                    break;
                case PUT:
                    builder.router().put(routeInfo.route().path(), handler);
                    break;
                case DELETE:
                    builder.router().delete(routeInfo.route().path(), handler);
                    break;
                case PATCH:
                    builder.router().patch(routeInfo.route().path(), handler);
                    break;
            }
        });
        return builder.router();
    }

    private ServerHandlers defaultHandlers() {
        return handlers(
                new TimeOutHandler(vertx, 1000),
                new ResponseTimeHandler(),
                new LogHandler(),
                new ContentTypeHandler(),
                new RouteMethodHandler(),
                new JsonResponseHandler(),
                new EndHandler())
                .exceptionHandler(
                        handlers(new TimeOutHandler(vertx, 1000),
                                new ResponseTimeHandler(),
                                new LogHandler(),
                                new ContentTypeHandler(),
                                new JsonResponseHandler()));
    }
}
