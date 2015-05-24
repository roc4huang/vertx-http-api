package vertx.handlers.http.api.examples.foo;


import vertx.handlers.http.api.activation.Provides;
import vertx.handlers.http.api.binding.Parameters;
import vertx.handlers.http.api.binding.impl.DefaultParameterBinder;
import vertx.handlers.http.api.controllers.Controller;
import vertx.handlers.http.api.routing.Get;
import vertx.handlers.http.api.routing.Post;
import vertx.handlers.http.api.routing.Route;

import java.util.function.Supplier;


public class FooController extends Controller {

    public final static String ROUTE_INDEX = "FooController.index";
    public final static String ROUTE_MY = "FooController.my";
    public final static String ROUTE_FOO = "FooController.examples";

    @Provides
    public static Supplier<Controller> newInstance() {
        return FooController::new;
    }

    @Get
    @Route(name = ROUTE_INDEX, path = "/test1/:arg0/:arg1/:arg2")
    @Parameters(binder = DefaultParameterBinder.class)
    public String test1(String a, String b, String c) {
        return a + b + c;
    }

    @Get
    @Route(name = ROUTE_MY, path = "/test2/:arg2/:arg1/:arg0")
    public String test2(String a, String b, String c) {
        return a + b + c;
    }

    @Post
    @Route(name = ROUTE_FOO, path = "/test3/:arg0")
    public String test3(String a, FooBar fooBar) {
        return String.format("%s %s", fooBar.toString(), a);
    }

}
