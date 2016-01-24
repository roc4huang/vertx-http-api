package com.spriet2000.vertx.http.api.binders.method;

import com.spriet2000.vertx.http.api.binders.method.impl.DefaultParametersBinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameters {
    Class<? extends ParametersBinder> binder() default DefaultParametersBinder.class;
}

