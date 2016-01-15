package vertx.handlers.http.api.builders;

import com.github.spriet2000.vertx.httprouter.Router;
import vertx.handlers.http.api.routing.impl.RouteRegistry;

public interface AppBuilder {

    Router router();

    RouteRegistry routes();

}
