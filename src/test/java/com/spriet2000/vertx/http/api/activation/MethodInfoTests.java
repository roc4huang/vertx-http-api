package com.spriet2000.vertx.http.api.activation;


import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.method.MethodInvoke;
import com.spriet2000.vertx.http.api.binding.parameter.Parameter;
import com.spriet2000.vertx.http.api.binding.method.Parameters;
import com.spriet2000.vertx.http.api.binding.method.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.method.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.controllers.Controller;
import com.spriet2000.vertx.http.api.routing.RoutingContext;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MethodInfoTests {

    @Test
    public void testMethodInfoNoneParamsWithoutAttributes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ControllerImpl.class.getMethod("noneParamsWithoutAttributes");
        MethodInfo methodInfo = new MethodInfo(method);

        assertEquals(0, methodInfo.parameters().length);
        assertEquals(ControllerImpl.class, methodInfo.getDeclaringClassActivator().newInstance().getClass());
        assertEquals(DefaultParametersBinder.class.getName(),
                methodInfo.parametersBinder().getClass().getName());
    }

    @Test
    public void testMethodInfoParamsWithParameterName() throws NoSuchMethodException {
        Method method = ControllerImpl.class.getMethod("oneParamsWithoutAttributes", String.class);
        MethodInfo methodInfo = new MethodInfo(method);

        assertEquals(1, methodInfo.parameters().length);
        assertEquals("arg0", methodInfo.parameters()[0].name());
        assertEquals(DefaultParametersBinder.class.getName(),
                methodInfo.parametersBinder().getClass().getName());
    }

    @Test
    public void testMethodInfoWithCustomAttributes() throws NoSuchMethodException {
        Method method = ControllerImpl.class.getMethod("customAttributes", String.class);
        MethodInfo methodInfo = new MethodInfo(method);

        assertEquals(1, methodInfo.parameters().length);
        assertEquals("test", methodInfo.parameters()[0].name());
        assertEquals(CustomParametersBinder.class.getName(),
                methodInfo.parametersBinder().getClass().getName());
    }

    @Test
    public void testMethodInvoke1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ControllerImpl.class.getMethod("method1", String.class, String.class);
        MethodInfo methodInfo = new MethodInfo(method);

        MethodInvoke invoke = new MethodInvoke(methodInfo);
        Object result = invoke.invoke("hello", "world");

        assertNotNull(result);
        assertEquals("hello world", result);
    }


    public static class ControllerImpl extends Controller {

        @Factory
        public static Supplier factory() {
            return ControllerImpl::new;
        }

        public String method1(String hi, String what) {
            return String.format("%s %s", hi, what);
        }

        @Parameters(binder = CustomParametersBinder.class)
        public String customAttributes(@Parameter(name = "test") String test) {
            return String.format("%s %s", test, test);
        }

        public String noneParamsWithoutAttributes() {
            return null;
        }

        public String oneParamsWithoutAttributes(String test) {
            return test;
        }
    }

    public static class CustomParametersBinder implements ParametersBinder {

        @Override
        public void bind(RoutingContext context, MethodInfo methodInfo, Value... arguments) {

        }
    }
}
