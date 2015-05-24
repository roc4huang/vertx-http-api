package vertx.handlers.http.api.activation;

import java.lang.reflect.InvocationTargetException;

public interface Activator {
    Object activate() throws InvocationTargetException, IllegalAccessException;



}
