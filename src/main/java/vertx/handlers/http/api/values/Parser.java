package vertx.handlers.http.api.values;

import io.vertx.core.Future;
import vertx.handlers.http.api.routing.impl.ParameterInfo;
import vertx.handlers.http.api.routing.impl.RoutingContext;

import java.util.Date;

public interface Parser {

    static boolean isSimple(Class<?> clazz) {
        return clazz.equals(String.class) ||
                clazz.equals(Boolean.class) ||
                clazz.equals(Integer.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Date.class);
    }

    void parse(ParameterInfo parameter, RoutingContext context, Future<Object> future);
}