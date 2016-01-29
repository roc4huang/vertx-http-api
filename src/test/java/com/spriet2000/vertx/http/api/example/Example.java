package com.spriet2000.vertx.http.api.example;

import com.github.spriet2000.vertx.handlers.http.server.utils.Runner;
import com.spriet2000.vertx.http.api.App;
import com.spriet2000.vertx.http.api.AppBuilder;
import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.method.Parameters;
import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.FromQuery;
import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.controllers.Controllers;
import com.spriet2000.vertx.http.api.controllers.impl.DefaultControllers;
import com.spriet2000.vertx.http.api.routing.Get;
import com.spriet2000.vertx.http.api.routing.Route;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.AppBuilder.builder;
import static com.spriet2000.vertx.http.api.controllers.Controllers.controllers;

public class Example extends AbstractVerticle {

    HttpServerOptions options = new HttpServerOptions().setPort(8080);

    public static void main(String[] args) {
        Runner.run(Example.class, new VertxOptions());
    }

    public void example() {

        App app = App.create(builder(vertx)
                .use(controllers(OrderController.class)));

        vertx.createHttpServer(options)
                .requestHandler(app)
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