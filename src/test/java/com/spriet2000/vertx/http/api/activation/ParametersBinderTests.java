package com.spriet2000.vertx.http.api.activation;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.FromCookie;
import com.spriet2000.vertx.http.api.binding.parameter.FromQuery;
import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.binding.parameters.Parameters;
import com.spriet2000.vertx.http.api.binding.parameters.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameters.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.RoutingContext;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static io.vertx.core.http.HttpHeaders.COOKIE;
import static org.junit.Assert.assertEquals;

public class ParametersBinderTests {

    @Test
    public void testParametersValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/hello?greeting=hello2&to=world2");
        RoutingContext context = new RoutingContext(request);
        context.parameters().add("greeting", "hello1");
        context.parameters().add("to", "world1");
        request.headers().add(COOKIE, "greeting=hello3; to=world3");

        Method method = ControllerImpl.class.getMethod("method1", String.class, String.class);
        MethodInfo info = new MethodInfo(method);
        ParametersBinder binder = info.parametersBinder();

        Object[] arguments = new Object[info.parameters().length];
        binder.bind(context, info, arguments);

        assertEquals("hello1", arguments[0]);
        assertEquals("world1", arguments[1]);
    }

    @Test
    public void testQueryValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/hello?greeting=hello2&to=world2");
        RoutingContext context = new RoutingContext(request);
        context.parameters().add("greeting", "hello1");
        context.parameters().add("to", "world1");
        request.headers().add(COOKIE, "greeting=hello3; to=world3");

        Method method = ControllerImpl.class.getMethod("method2", String.class, String.class);
        MethodInfo info = new MethodInfo(method);
        ParametersBinder binder = info.parametersBinder();

        Object[] arguments = new Object[info.parameters().length];
        binder.bind(context, info, arguments);

        assertEquals("hello2", arguments[0]);
        assertEquals("world2", arguments[1]);
    }

    @Test
    public void testCookieValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/hello?greeting=hello2&to=world2");
        RoutingContext context = new RoutingContext(request);
        context.parameters().add("greeting", "hello1");
        context.parameters().add("to", "world1");
        request.headers().add(COOKIE, "greeting=hello3; to=world3");

        Method method = ControllerImpl.class.getMethod("method3", String.class, String.class);
        MethodInfo info = new MethodInfo(method);
        ParametersBinder binder = info.parametersBinder();

        Object[] arguments = new Object[info.parameters().length];
        binder.bind(context, info, arguments);

        assertEquals("hello3", arguments[0]);
        assertEquals("world3", arguments[1]);
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


        @Parameters(binder = DefaultParametersBinder.class)
        public String method2(@FromQuery @Parameter(name = "greeting") String hi,
                              @FromQuery @Parameter(name = "to") String what) {
            return String.format("%s %s", hi, what);
        }

        @Parameters(binder = DefaultParametersBinder.class)
        public String method3(@FromCookie @Parameter(name = "greeting") String hi,
                              @FromCookie @Parameter(name = "to") String what) {
            return String.format("%s %s", hi, what);
        }
    }
}
