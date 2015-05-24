package vertx.handlers.http.api.ext.impl;

import com.github.spriet2000.vertx.handlers.http.server.ServerController;
import com.github.spriet2000.vertx.handlers.http.server.ServerHandler;
import io.vertx.core.Handler;
import vertx.handlers.http.api.binding.ParameterBinder;
import vertx.handlers.http.api.controllers.Controller;
import vertx.handlers.http.api.routing.impl.ControllerInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;

import java.lang.reflect.InvocationTargetException;


public class RouteMethodHandler implements ServerController {

    @Override
    public ServerHandler<WebAppContext> handle(Handler fail, Handler next) {
        return (req, res, context) -> {
            RoutingContext routingContext = context.routingContext();
            ControllerInfo controllerInfo = routingContext.controller();
            ParameterBinder binder = null;
            try {
                binder = (ParameterBinder) controllerInfo.methodInfo().parameterBinderActivator().activate();
            } catch (InvocationTargetException | IllegalAccessException e) {
                fail.handle(new ErrorContext(e));
            }
            assert binder != null;
            try {
                binder.bind(routingContext, parameters -> {
                    try {
                        Controller controller = (Controller) controllerInfo.activator().activate();
                        context.result(controllerInfo.methodInfo().invoke(controller, parameters));
                        next.handle(context);

                    } catch (InvocationTargetException | IllegalAccessException e) {
                        fail.handle(new ErrorContext(e));
                    }
                });
            } catch (InvocationTargetException | IllegalAccessException e) {
                fail.handle(new ErrorContext(e));
            }
        };
    }
}
