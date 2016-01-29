package com.spriet2000.vertx.http.api.tests;

import com.spriet2000.vertx.http.api.AppTestBase;
import com.spriet2000.vertx.http.api.controllers.Controllers;
import com.spriet2000.vertx.http.api.example.Example;
import io.vertx.core.http.HttpMethod;
import org.junit.Test;

import static com.spriet2000.vertx.http.api.AppBuilder.builder;
import static com.spriet2000.vertx.http.api.controllers.Controllers.controllers;


public class RequestTest1 extends AppTestBase {

    @Test
    public void test() throws Exception {

        Controllers controllers = controllers(Example.OrderController.class);

        app.configure(builder(vertx).use(controllers));

        testRequest(HttpMethod.GET, "/", 200, "OK");
    }
}
