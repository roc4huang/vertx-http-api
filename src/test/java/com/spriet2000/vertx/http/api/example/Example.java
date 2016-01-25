package com.spriet2000.vertx.http.api.example;

import com.spriet2000.vertx.http.api.App;
import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.method.Parameters;
import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.FromQuery;
import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.impl.Controllers;
import com.spriet2000.vertx.http.api.routing.Get;
import com.spriet2000.vertx.http.api.routing.Route;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.test.core.VertxTestBase;
import org.junit.Test;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.App.webApp;
import static com.spriet2000.vertx.http.api.reflection.ControllerScanner.scanClassPaths;

public class Example extends VertxTestBase {

    HttpServerOptions options = new HttpServerOptions().setPort(8080);
    @Test
    public void example(){

        Controllers controllers =
                scanClassPaths("com.spriet2000.vertx.http.api.example");

        App app = webApp(vertx).configure(a -> {
            a.use(controllers);
        });

        vertx.createHttpServer(options).requestHandler(app)
                .listen();
    }

    public static class OrderController extends Controller {

        public final static String ROUTE_ORDER = "FooController.order";

        @Factory
        public static Supplier<Controller> newInstance() {
            return OrderController::new;
        }

        @Get
        @Route(name = ROUTE_ORDER, path = "/order/:beer")
        @Parameters(binder = DefaultParametersBinder.class)
        public String order(@Parameter(name = "beer") String beer,
                            @FromQuery @Parameter(name = "times") int times) {
            return String.format("Order %s %s", times, beer);
        }

    }

}