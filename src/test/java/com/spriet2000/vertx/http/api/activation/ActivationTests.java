package com.spriet2000.vertx.http.api.activation;

import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static org.junit.Assert.assertNotNull;


public class ActivationTests {

    @Test
    public void testResolveWithFactory() throws InvocationTargetException, IllegalAccessException {
        DefaultActivator activator = new DefaultActivator(ControllerWithFactoryImpl.class);
        Object object =  activator.create();
        assertNotNull(object);
    }

    @Test
    public void testResolveWithoutFactory() throws InvocationTargetException, IllegalAccessException {
        DefaultActivator activator = new DefaultActivator(ControllerWithoutFactoryImpl.class);
        Object object =  activator.create();
        assertNotNull(object);
    }

    public static class ControllerWithFactoryImpl {

        @Factory
        public static Supplier supply(){
            return ControllerWithFactoryImpl::new;
        }

    }

    public static class ControllerWithoutFactoryImpl { }
}
