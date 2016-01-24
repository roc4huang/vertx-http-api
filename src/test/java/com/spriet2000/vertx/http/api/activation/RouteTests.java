package com.spriet2000.vertx.http.api.activation;

import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.Route;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;


public class RouteTests {

    @Test
    public void testMethodInfoNoneParamsWithoutAttributes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    }


    public static class ControllerImpl extends Controller {

        @Factory
        public static Supplier factory() {
            return ControllerImpl::new;
        }

        @Route(name = "greeting1", path = "/:greeting/:who")
        public String greeting(@Parameter(name = "greeting") String greeting,
                               @Parameter(name = "who") String who) {
            return String.format("%s %s", greeting, who);
        }

    }
}
