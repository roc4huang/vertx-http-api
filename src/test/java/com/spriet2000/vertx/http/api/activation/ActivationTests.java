package com.spriet2000.vertx.http.api.activation;

import com.spriet2000.vertx.http.api.activation.impl.DefaultActivator;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static org.junit.Assert.assertNotNull;


public class ActivationTests {

    @Test
    public void testResolveWithFactory() throws InvocationTargetException, IllegalAccessException {
        DefaultActivator activator = new DefaultActivator(ClassWithFactoryImpl.class);
        Object object =  activator.newInstance();
        assertNotNull(object);
    }

    @Test
    public void testResolveWithoutFactory() throws InvocationTargetException, IllegalAccessException {
        DefaultActivator activator = new DefaultActivator(ClassWithoutFactoryImpl.class);
        Object object =  activator.newInstance();
        assertNotNull(object);
    }

    public static class ClassWithFactoryImpl {

        @Factory
        public static Supplier supply(){
            return ClassWithFactoryImpl::new;
        }

    }

    public static class ClassWithoutFactoryImpl { }
}
