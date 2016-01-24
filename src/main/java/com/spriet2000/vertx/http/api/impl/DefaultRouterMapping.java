package com.spriet2000.vertx.http.api.impl;

import com.github.spriet2000.vertx.httprouter.RouteHandler;
import com.spriet2000.vertx.http.api.Router;
import com.spriet2000.vertx.http.api.binders.method.MethodInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import com.spriet2000.vertx.http.api.routing.impl.RouteInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteRegistry;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Map;


public class DefaultRouterMapping implements Router {

    static Logger logger = LoggerFactory.getLogger(DefaultRouterMapping.class);

    com.github.spriet2000.vertx.httprouter.Router router =
            com.github.spriet2000.vertx.httprouter.Router.router();

    @Override
    public Handler<HttpServerRequest> map(RouteRegistry routeRegistry, Handler<RouteContext> consumer) {

        logger.info("Mapping routes");

        com.github.spriet2000.vertx.httprouter.Router router =
                com.github.spriet2000.vertx.httprouter.Router.router();

        for (Map.Entry<RouteInfo, MethodInfo> entry : routeRegistry.entrySet()) {

            logger.info("map handler for route");

            RouteInfo route = entry.getKey();
            MethodInfo method = entry.getValue();

            RouteHandler handler = (req, params) -> {
                consumer.handle(new RouteContext(route, method, req, params));
            };

            switch (route.httpMethod()) {
                case GET:
                    router.get(route.route().path(), handler);
                case HEAD:
                    router.head(route.route().path(), handler);
                case POST:
                    router.post(route.route().path(), handler);
                case PUT:
                    router.put(route.route().path(), handler);
                case DELETE:
                    router.delete(route.route().path(), handler);
                case PATCH:
                    router.patch(route.route().path(), handler);
            }
        }

        logger.info("Mapping routes completed");

        return router;
    }
}
