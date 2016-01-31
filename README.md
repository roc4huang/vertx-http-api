# Vert.x http api
Rest/mvc like framework build upon vertx-http-handlers. The application registers controllers and makes them available for http requests.

[![Build Status](https://travis-ci.org/spriet2000/vertx-http-api.svg?branch=master)](https://travis-ci.org/spriet2000/vertx-handlers-http-api)

##  Experimental

### Verticle

At verticle start the verticle scans for controller instances on a given path and builds a registry with route information.

```

App app = App.create(builder(vertx)
        .use(controllers(OrderController.class)));

vertx.createHttpServer(options)
        .requestHandler(app)
        .listen();

```

### Controller 

The @FromCookie specifies location for an argument

The result of a method is serialized depending of the request content-type. For example json.

Vertx and context are available in controller.

```

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

```
