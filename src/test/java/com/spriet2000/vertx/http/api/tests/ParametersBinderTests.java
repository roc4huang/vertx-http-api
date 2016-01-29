package com.spriet2000.vertx.http.api.tests;

import com.spriet2000.vertx.http.api.TestHttpServerRequest;
import com.spriet2000.vertx.http.api.activation.Factory;
import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.Parameters;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.parameter.FromCookie;
import com.spriet2000.vertx.http.api.binding.parameter.FromQuery;
import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.spriet2000.vertx.http.api.binding.value.Converter.convert;
import static com.spriet2000.vertx.http.api.reflection.MethodInfoHelper.toMethodInfo;
import static io.vertx.core.http.HttpHeaders.COOKIE;
import static org.junit.Assert.assertEquals;

public class ParametersBinderTests {

    @Test
    public void testParametersValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("greeting", "hello1");
        parameters.put("to", "world1");

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/hello?greeting=hello2&to=world2");
        request.headers().add(COOKIE, "greeting=hello3; to=world3");

        Method method = ControllerImpl.class.getMethod("method1", String.class, String.class);
        MethodInfo info = toMethodInfo(method);

        RouteContext context = new RouteContext(null, info, request, parameters);

        ParametersBinder binder = info.parametersBinder();
        Value[] arguments = new Value[info.parameters().length];
        binder.bind(context, arguments);


        assertEquals("hello1", convert(arguments[0].getValue(), arguments[0].getType()));
        assertEquals("world1", convert(arguments[1].getValue(), arguments[1].getType()));
    }

    @Test
    public void testQueryValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("greeting", "hello1");
        parameters.put("to", "world1");

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/hello?greeting=hello2&to=world2");
        request.headers().add(COOKIE, "greeting=hello3; to=world3");

        Method method = ControllerImpl.class.getMethod("method2", String.class, String.class);
        MethodInfo info = toMethodInfo(method);

        RouteContext context = new RouteContext(null, info, request, parameters);

        ParametersBinder binder = info.parametersBinder();
        Value[] arguments = new Value[info.parameters().length];
        binder.bind(context, arguments);

        assertEquals("hello2", convert(arguments[0].getValue(), arguments[0].getType()));
        assertEquals("world2", convert(arguments[1].getValue(), arguments[1].getType()));
    }

    @Test
    public void testCookieValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("greeting", "hello1");
        parameters.put("to", "world1");

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/hello?greeting=hello2&to=world2");
        request.headers().add(COOKIE, "greeting=hello3; to=world3");

        Method method = ControllerImpl.class.getMethod("method3", String.class, String.class);
        MethodInfo info = toMethodInfo(method);

        RouteContext context = new RouteContext(null, info, request, parameters);

        ParametersBinder binder = info.parametersBinder();
        Value[] arguments = new Value[info.parameters().length];
        binder.bind(context, arguments);

        assertEquals("hello3", convert(arguments[0].getValue(), arguments[0].getType()));
        assertEquals("world3", convert(arguments[1].getValue(), arguments[1].getType()));
    }

    @Test
    public void testParametersConvertedIntValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("times", "20");

        HttpServerRequest request = new TestHttpServerRequest(HttpMethod.GET, "/");

        Method method = ControllerImpl.class.getMethod("method4", int.class);
        MethodInfo info = toMethodInfo(method);

        RouteContext context = new RouteContext(null, info, request, parameters);

        ParametersBinder binder = info.parametersBinder();
        Value[] arguments = new Value[info.parameters().length];
        binder.bind(context, arguments);

        assertEquals(20, convert(arguments[0].getValue(), arguments[0].getType()));
    }

    public static class ControllerImpl extends Controller {

        @Factory
        public static Supplier supplier() {
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

        @Parameters(binder = DefaultParametersBinder.class)
        public String method4(@Parameter(name = "times") int times) {
            return String.format("times %s", times);
        }
    }
}