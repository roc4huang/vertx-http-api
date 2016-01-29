package com.spriet2000.vertx.http.api;

import com.spriet2000.vertx.http.api.controllers.impl.DefaultControllers;
import com.spriet2000.vertx.http.api.example.Example;
import com.spriet2000.vertx.http.api.helpers.WebTestBase;
import io.vertx.core.http.HttpMethod;
import org.junit.Test;

import static com.spriet2000.vertx.http.api.AppBuilder.builder;


public class RequestTest1 extends WebTestBase {

    @Test
    public void test() throws Exception {

        DefaultControllers controllers = new DefaultControllers();
        controllers.add(Example.OrderController.class);

        app.configure(builder(vertx).use(controllers));

        testRequest(HttpMethod.GET, "/", 200, "OK");
    }
}
