package com.spriet2000.vertx.http.api.activation;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

public class TestHttpServerRequest implements HttpServerRequest {

    private final HttpMethod method;
    private final String path;
    private final String query;

    public TestHttpServerRequest(HttpMethod method, String path) {

        this.method = method;
        this.path = path;
        String[] pair = path.split("\\?");
        this.query = pair.length > 1 ? pair[1] : "";
    }

    @Override
    public HttpServerRequest exceptionHandler(Handler<Throwable> handler) {
        return null;
    }

    @Override
    public HttpServerRequest handler(Handler<Buffer> handler) {
        return null;
    }

    @Override
    public HttpServerRequest pause() {
        return null;
    }

    @Override
    public HttpServerRequest resume() {
        return null;
    }

    @Override
    public HttpServerRequest endHandler(Handler<Void> handler) {
        return null;
    }

    @Override
    public HttpVersion version() {
        return null;
    }

    @Override
    public HttpMethod method() {
        return method;
    }

    @Override
    public String uri() {
        return null;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public String query() {
        return query;
    }

    @Override
    public HttpServerResponse response() {
        return null;
    }

    @Override
    public MultiMap headers() {
        return null;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public String getHeader(CharSequence charSequence) {
        return null;
    }

    @Override
    public MultiMap params() {
        return null;
    }

    @Override
    public String getParam(String s) {
        return null;
    }

    @Override
    public SocketAddress remoteAddress() {
        return null;
    }

    @Override
    public SocketAddress localAddress() {
        return null;
    }

    @Override
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return new X509Certificate[0];
    }

    @Override
    public String absoluteURI() {
        return null;
    }

    @Override
    public HttpServerRequest bodyHandler(Handler<Buffer> handler) {
        return null;
    }

    @Override
    public NetSocket netSocket() {
        return null;
    }

    @Override
    public HttpServerRequest setExpectMultipart(boolean b) {
        return null;
    }

    @Override
    public boolean isExpectMultipart() {
        return false;
    }

    @Override
    public HttpServerRequest uploadHandler(Handler<HttpServerFileUpload> handler) {
        return null;
    }

    @Override
    public MultiMap formAttributes() {
        return null;
    }

    @Override
    public String getFormAttribute(String s) {
        return null;
    }

    @Override
    public ServerWebSocket upgrade() {
        return null;
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    static <T> T coalesce(T a, T b) {
        return a == null ? b : a;
    }
}
