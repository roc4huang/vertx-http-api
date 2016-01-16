package com.spriet2000.vertx.http.api.activation;


import com.spriet2000.vertx.http.api.binding.ParameterBinder;
import com.spriet2000.vertx.http.api.binding.ParametersBinder;
import com.spriet2000.vertx.http.api.binding.impl.DefaultParameterBinder;
import com.spriet2000.vertx.http.api.reflection.MethodInfo;
import com.spriet2000.vertx.http.api.reflection.Parameter;
import com.spriet2000.vertx.http.api.reflection.ParameterInfo;
import com.spriet2000.vertx.http.api.reflection.Parameters;
import com.spriet2000.vertx.http.api.binding.impl.DefaultParametersBinder;
import com.spriet2000.vertx.http.api.controllers.Controller;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class ReflectionTests {

    @Test
    public void testMethodInfoNoneParamsWithoutAttributes() throws NoSuchMethodException {
        Method method = ControllerImpl.class.getMethod("noneParamsWithoutAttributes");
        MethodInfo methodInfo = new MethodInfo(method);

        assertEquals(0, methodInfo.getParameters().length);
        assertEquals(ControllerImpl.class, methodInfo.getDeclaringClass());
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

    public static class ControllerImpl extends Controller {

        @Parameters(binder = CustomParametersBinder.class)
        public Result customAttributes(@Parameter(name = "test") String test){
            return new Result();
        }

        public Result noneParamsWithoutAttributes(){
            return new Result();
        }

        public Result oneParamsWithoutAttributes(String test){
            return new Result();
        }

        @Factory
        public static Supplier factory(){
            return ControllerImpl::new;
        }
    }

    public static class Result {

    }

    public static class CustomParametersBinder implements ParametersBinder {

        @Override
        public void bind() {

        }
    }
}
