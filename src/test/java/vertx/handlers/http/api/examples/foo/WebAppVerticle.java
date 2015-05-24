package vertx.handlers.http.api.examples.foo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import vertx.handlers.http.api.WebApp;
import vertx.handlers.http.api.builders.impl.DefaultAppBuilder;

import static com.github.spriet2000.vertx.httprouter.Router.router;
import static vertx.handlers.http.api.WebApp.webApp;
import static vertx.handlers.http.api.controllers.impl.ControllerScanner.scanClassPaths;

public class WebAppVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Hosting.run(WebAppVerticle.class, new VertxOptions());
    }

    @Override
    public void start() throws Exception {
        WebApp app = webApp(vertx).configure(new DefaultAppBuilder()
                .useControllers(
                        scanClassPaths("vertx.handlers.http.api.examples"))
                .useRouter(router()));

        vertx.createHttpServer(new HttpServerOptions().setPort(8080))
                .requestHandler(app)
                .listen();
    }
}
