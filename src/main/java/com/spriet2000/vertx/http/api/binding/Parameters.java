package com.spriet2000.vertx.http.api.binding;

import com.spriet2000.vertx.http.api.binding.impl.DefaultParametersBinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameters {
    Class<? extends ParametersBinder> binder() default DefaultParametersBinder.class;
}

