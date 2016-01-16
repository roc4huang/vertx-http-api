package com.spriet2000.vertx.http.api.activation;

import com.spriet2000.vertx.http.api.binding.*;
import com.spriet2000.vertx.http.api.binding.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.RoutingContext;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;


public class ParametersBinderTests {

    @Test
    public void testParametersBinder() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/");
        RoutingContext context = new RoutingContext(request);
        context.parameters().add("greeting", "hello");
        context.parameters().add("to", "world");

        Method method = ControllerImpl.class.getMethod("method1", String.class, String.class);
        MethodInfo info = new MethodInfo(method);

        Object[] arguments = new Object[info.parameters().length];
        ParametersBinder binder = info.parametersBinder();
        binder.bind(context, info, arguments);

        assertEquals("hello", arguments[0]);
        assertEquals("world", arguments[1]);
    }


    public static class ControllerImpl extends Controller {

        @Factory
        public static Supplier factory() {
            return ControllerImpl::new;
        }

        @Parameters(binder = DefaultParametersBinder.class)
        public String method1(@Parameter(name = "greeting") String hi,
                              @Parameter(name = "to") String what) {
            return String.format("%s %s", hi, what);
        }
    }
}
