package com.spriet2000.vertx.http.api.example;

import com.spriet2000.vertx.http.api.App;
import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.method.Parameters;
import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.FromCookie;
import com.spriet2000.vertx.http.api.binding.parameter.FromQuery;
import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.Get;
import com.spriet2000.vertx.http.api.routing.Route;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.AppBuilder.builder;
import static com.spriet2000.vertx.http.api.controllers.Controllers.controllers;

public class Example extends AbstractVerticle {

    static Logger logger = LoggerFactory.getLogger(OrderController.class);

    HttpServerOptions options = new HttpServerOptions().setPort(8080);

    public void example() {

        App app = App.create(builder(vertx)
                .use(controllers(OrderController.class)));

        vertx.createHttpServer(options)
                .requestHandler(app)
                .listen();
    }

    public static class OrderController extends Controller {

        public final static String ROUTE_ORDER = "OrderController.order";

        @Get
        @Route(name = ROUTE_ORDER, path = "/order/:beer")
        public String order(@Parameter(name = "beer") String beer,
                            @FromCookie @Parameter(name = "amount") int amount) {

            logger.info(String.format("path: %s, name: %s",
                    context().routeInfo().path(), context().routeInfo().name()));

            return String.format("Order %s %s", amount, beer);
        }
    }
}