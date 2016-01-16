package com.spriet2000.vertx.http.api.activation;


import com.spriet2000.vertx.http.api.binding.*;
import com.spriet2000.vertx.http.api.binding.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.controllers.Controller;
import io.vertx.core.http.HttpServerRequest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReflectionTests {

    @Test
    public void testMethodInfoNoneParamsWithoutAttributes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ControllerImpl.class.getMethod("noneParamsWithoutAttributes");
        MethodInfo methodInfo = new MethodInfo(method);

        assertEquals(0, methodInfo.getParameters().length);
        assertEquals(ControllerImpl.class, methodInfo.getDeclaringClassActivator().newInstance().getClass());
        assertEquals(DefaultParametersBinder.class.getName(),
                methodInfo.getParametersBinder().getClass().getName());
    }

    @Test
    public void testMethodInfoParamsWithParameterName() throws NoSuchMethodException {
        Method method = ControllerImpl.class.getMethod("oneParamsWithoutAttributes", String.class);
        ParameterInfo parameterInfo = new ParameterInfo(method.getParameters()[0]);
        MethodInfo methodInfo = new MethodInfo(method, parameterInfo);

        assertEquals(1, methodInfo.getParameters().length);
        assertEquals("arg0", methodInfo.getParameters()[0].getIdentifier());
        assertEquals(DefaultParametersBinder.class.getName(),
                methodInfo.getParametersBinder().getClass().getName());
    }

    @Test
    public void testMethodInfoWithCustomAttributes() throws NoSuchMethodException {
        Method method = ControllerImpl.class.getMethod("customAttributes", String.class);
        ParameterInfo parameterInfo = new ParameterInfo(method.getParameters()[0]);
        MethodInfo methodInfo = new MethodInfo(method, parameterInfo);

        assertEquals(1, methodInfo.getParameters().length);
        assertEquals("test", methodInfo.getParameters()[0].getIdentifier());
        assertEquals(CustomParametersBinder.class.getName(),
                methodInfo.getParametersBinder().getClass().getName());
    }

    @Test
    public void testMethodInvoke1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ControllerImpl.class.getMethod("method1", String.class, String.class);
        ParameterInfo parameterInfo1 = new ParameterInfo(method.getParameters()[0]);
        ParameterInfo parameterInfo2 = new ParameterInfo(method.getParameters()[1]);
        MethodInfo methodInfo = new MethodInfo(method, parameterInfo1, parameterInfo2);

        MethodInvoke invoke = new MethodInvoke(methodInfo, "hello", "world");

        Object result = invoke.invoke();

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
        public void bind(HttpServerRequest request, MethodInfo methodInfo) {

        }
    }
}
