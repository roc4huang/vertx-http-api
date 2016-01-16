package com.spriet2000.vertx.http.api.activation;

import java.lang.reflect.InvocationTargetException;

public interface Activator {
    Object newInstance() throws InvocationTargetException, IllegalAccessException;

}
