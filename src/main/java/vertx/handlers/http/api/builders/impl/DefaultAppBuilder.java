package vertx.handlers.http.api.builders.impl;

import com.github.spriet2000.vertx.handlers.http.server.ServerHandlers;
import com.github.spriet2000.vertx.httprouter.Router;
import vertx.handlers.http.api.builders.AppBuilder;
import vertx.handlers.http.api.controllers.ControllerRegistryBuilder;
import vertx.handlers.http.api.controllers.impl.ControllerRegistry;
import vertx.handlers.http.api.routing.impl.RouteRegistry;

import static vertx.handlers.http.api.routing.impl.RouteScanner.buildRoutes;

public class DefaultAppBuilder implements AppBuilder {
    private ControllerRegistry controllerRegistry;
    private Router router;
    private ServerHandlers handlers;

    public DefaultAppBuilder useRouter(Router router) {
        this.router = router;
        return this;
    }

    public DefaultAppBuilder useControllers(ControllerRegistryBuilder builder) {
        controllerRegistry = builder.build();
        return this;
    }

    public DefaultAppBuilder setRequestHandlers(ServerHandlers handlers) {
        this.handlers = handlers;
        return this;
    }

    @Override
    public Router router() {
        return router;
    }

    @Override
    public RouteRegistry routes() {
        RouteRegistry routeRegistry = new RouteRegistry();
        buildRoutes(controllerRegistry, routeRegistry);
        return routeRegistry;
    }

    @Override
    public ServerHandlers requestHandlers() {
        return handlers;
    }

}
