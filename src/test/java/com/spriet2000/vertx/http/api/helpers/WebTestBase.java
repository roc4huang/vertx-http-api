package com.spriet2000.vertx.http.api.helpers;

import com.spriet2000.vertx.http.api.App;
import com.spriet2000.vertx.http.api.impl.DefaultApp;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.test.core.VertxTestBase;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class WebTestBase extends VertxTestBase {


    protected HttpServer server;
    protected HttpClient client;
    protected App app;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        app = new DefaultApp();
        server = vertx.createHttpServer(new HttpServerOptions().setPort(8080).setHost("localhost"));
        client = vertx.createHttpClient(new HttpClientOptions().setDefaultPort(8080));
        CountDownLatch latch = new CountDownLatch(1);
        server.requestHandler(app::accept).listen(onSuccess(res -> {
            latch.countDown();
        }));
        awaitLatch(latch);
    }

    @Override
    public void tearDown() throws Exception {
        if (client != null) {
            client.close();
        }
        if (server != null) {
            CountDownLatch latch = new CountDownLatch(1);
            server.close((asyncResult) -> {
                assertTrue(asyncResult.succeeded());
                latch.countDown();
            });
            awaitLatch(latch);
        }
        super.tearDown();
    }

    protected void testRequest(HttpMethod method, String path, int statusCode, String statusMessage) throws Exception {
        testRequest(method, path, null, statusCode, statusMessage, null);
    }

    protected void testRequest(HttpMethod method, String path, int statusCode, String statusMessage,
                               String responseBody) throws Exception {
        testRequest(method, path, null, statusCode, statusMessage, responseBody);
    }

    protected void testRequest(HttpMethod method, String path, int statusCode, String statusMessage,
                               Buffer responseBody) throws Exception {
        testRequestBuffer(method, path, null, null, statusCode, statusMessage, responseBody);
    }

    protected void testRequestWithContentType(HttpMethod method, String path, String contentType, int statusCode, String statusMessage) throws Exception {
        testRequest(method, path, req -> req.putHeader("content-type", contentType), statusCode, statusMessage, null);
    }

    protected void testRequestWithAccepts(HttpMethod method, String path, String accepts, int statusCode, String statusMessage) throws Exception {
        testRequest(method, path, req -> req.putHeader("accept", accepts), statusCode, statusMessage, null);
    }

    protected void testRequestWithCookies(HttpMethod method, String path, String cookieHeader, int statusCode, String statusMessage) throws Exception {
        testRequest(method, path, req -> req.putHeader("cookie", cookieHeader), statusCode, statusMessage, null);
    }

    protected void testRequest(HttpMethod method, String path, Consumer<HttpClientRequest> requestAction,
                               int statusCode, String statusMessage,
                               String responseBody) throws Exception {
        testRequest(method, path, requestAction, null, statusCode, statusMessage, responseBody);
    }

    protected void testRequest(HttpMethod method, String path, Consumer<HttpClientRequest> requestAction, Consumer<HttpClientResponse> responseAction,
                               int statusCode, String statusMessage,
                               String responseBody) throws Exception {
        testRequestBuffer(method, path, requestAction, responseAction, statusCode, statusMessage, responseBody != null ? Buffer.buffer(responseBody) : null);
    }

    protected void testRequestBuffer(HttpMethod method, String path, Consumer<HttpClientRequest> requestAction, Consumer<HttpClientResponse> responseAction,
                                     int statusCode, String statusMessage,
                                     Buffer responseBodyBuffer) throws Exception {
        testRequestBuffer(client, method, 8080, path, requestAction, responseAction, statusCode, statusMessage, responseBodyBuffer);
    }

    protected void testRequestBuffer(HttpClient client, HttpMethod method, int port, String path, Consumer<HttpClientRequest> requestAction, Consumer<HttpClientResponse> responseAction,
                                     int statusCode, String statusMessage,
                                     Buffer responseBodyBuffer) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        HttpClientRequest req = client.request(method, port, "localhost", path, resp -> {
            assertEquals(statusCode, resp.statusCode());
            assertEquals(statusMessage, resp.statusMessage());
            if (responseAction != null) {
                responseAction.accept(resp);
            }
            if (responseBodyBuffer == null) {
                latch.countDown();
            } else {
                resp.bodyHandler(buff -> {
                    assertEquals(responseBodyBuffer, buff);
                    latch.countDown();
                });
            }
        });
        if (requestAction != null) {
            requestAction.accept(req);
        }
        req.end();
        awaitLatch(latch);
    }


}
