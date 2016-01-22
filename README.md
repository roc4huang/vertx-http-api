# Vert.x http api
Rest/mvc like framework build upon vertx-http-handlers. The application registers controllers and makes them available for http requests.

[![Build Status](https://travis-ci.org/spriet2000/vertx-http-api.svg?branch=master)](https://travis-ci.org/spriet2000/vertx-handlers-http-api)

##  Experimental

### Verticle

At verticle start the verticle scans for controller instances on a given path and builds a registry with route information.

```

public void start() throws Exception {
    WebApp app = webApp(vertx).configure(new DefaultAppBuilder()
            .useControllers(
                    scanClassPaths("vertx.handlers.http.examples.webapp"))
            .useRouter(router()));

    vertx.createHttpServer(new HttpServerOptions().setPort(8080))
            .requestHandler(app)
            .listen();
}

```

### Controller 

The @Parameters is optional. It demonstrates the plugable binders.

The @Provides acts a factory method for the controller. A controller instance is made for every request.

The result of a method is serialized depending of the request content-type. For example json.

The route names should be unique and can for example be to used to generate urls.

```

public class FooController extends Controller {

    public final static String ROUTE_INDEX = "FooController.index";
    public final static String ROUTE_FOO = "FooController.foo";

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

    @Post
    @Route(name = ROUTE_FOO, path = "/test2/:arg0")
    public String test2(String a, FooBar fooBar) {
        return String.format("%s %s", fooBar.toString(), a);
    }
}

```
