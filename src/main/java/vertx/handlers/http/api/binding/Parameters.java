package vertx.handlers.http.api.binding;

import vertx.handlers.http.api.binding.impl.DefaultParameterBinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameters {
    Class<? extends ParameterBinder> binder() default DefaultParameterBinder.class;
}

