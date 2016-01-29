package com.spriet2000.vertx.http.api.tests;

import com.spriet2000.vertx.http.api.AppTestBase;
import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.parameter.FromQuery;
import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.Get;
import com.spriet2000.vertx.http.api.routing.Route;
import io.vertx.core.http.HttpMethod;
import org.junit.Test;

import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.AppBuilder.builder;
import static com.spriet2000.vertx.http.api.controllers.Controllers.controllers;


public class RequestTest1 extends AppTestBase {

    @Test
    public void orderBeerTest() throws Exception {

        app.builder(builder(vertx).use(controllers(OrderController.class)));

        testRequest(HttpMethod.GET, "/order/beer?amount=10", 200, "OK", "Order 10 beer");
    }

    public static class OrderController extends Controller {

        public final static String ROUTE_ORDER = "FooController.order";

        @Factory
        public static Supplier<Controller> supplier() {
            return OrderController::new;
        }

        @Get
        @Route(name = ROUTE_ORDER, path = "/order/:beer")
        public String order(@Parameter(name = "beer") String beer,
                            @FromQuery @Parameter(name = "amount") int amount) {
            return String.format("Order %s %s", amount, beer);
        }

    }
}
