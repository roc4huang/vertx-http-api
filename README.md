# Vert.x http api
Rest/mvc like framework build upon vertx-http-handlers. The application registers controllers and makes them available for http requests.

[![Build Status](https://travis-ci.org/spriet2000/vertx-http-api.svg?branch=master)](https://travis-ci.org/spriet2000/vertx-handlers-http-api)

##  Experimental

### Verticle

At verticle start the verticle scans for controller instances on a given path and builds a registry with route information.

```

   Controllers controllers = scanClassPaths("com.spriet2000.vertx.http.api.example");
        
   App app = webApp(vertx).configure(builder().use(controllers));
        
   vertx.createHttpServer(options).requestHandler(app).listen();

```

### Controller 

The @Factory is a factory method for the controller. A controller instance is made for every request.

The @FromQuery specifies location for an argument

The result of a method is serialized depending of the request content-type. For example json.

The route names should be unique and can for example be to used to generate urls.

```

    public static class OrderController extends Controller {

        public final static String ROUTE_ORDER = "FooController.order";
        
        @Factory
        public static Supplier<Controller> newInstance() {
            return OrderController::new;
        }

        @Get
        @Route(name = ROUTE_ORDER, path = "/order/:beer")
        public String order(@Parameter(name = ":beer") String beer,
                            @FromQuery @Parameter(name = "times") int times) {
            return String.format("Order %s %s", times, beer);
        }

    }

```
