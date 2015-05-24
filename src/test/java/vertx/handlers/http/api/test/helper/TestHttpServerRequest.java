package vertx.handlers.http.api.test.helper;

import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.core.http.impl.HeadersAdaptor;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

import static org.mockito.Mockito.*;


public class TestHttpServerRequest implements HttpServerRequest {

    private Handler<Buffer> bodyHandler;
    private Handler<Buffer> handler;
    private MultiMap headers = new HeadersAdaptor(HttpHeaders.EMPTY_HEADERS);
    private Handler<Void> endHandler;

    public static TestHttpServerRequest jsonRequest(String path, HttpMethod method) {
        TestHttpServerRequest request = spy(new TestHttpServerRequest());
        when(request.path()).thenReturn(path);
        when(request.method()).thenReturn(method);
        when(request.headers()).thenReturn(mock(MultiMap.class));
        when(request.params()).thenReturn(mock(MultiMap.class));
        when(request.headers().get(HttpHeaders.Names.CONTENT_TYPE)).thenReturn("application/json");
        return request;
    }

    @Override
    public HttpServerRequest exceptionHandler(Handler<Throwable> handler) {
        return null;
    }

    @Override
    public HttpServerRequest handler(Handler<Buffer> handler) {
        this.handler = handler;
        return this;
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
    public HttpServerRequest endHandler(Handler<Void> endHandler) {
        this.endHandler = endHandler;
        return this;
    }

    @Override
    public HttpVersion version() {
        return null;
    }

    @Override
    public HttpMethod method() {
        return null;
    }

    @Override
    public String uri() {
        return null;
    }

    @Override
    public String path() {
        return null;
    }

    @Override
    public String query() {
        return null;
    }

    @Override
    public HttpServerResponse response() {
        return null;
    }

    @Override
    public MultiMap headers() {
        return headers;
    }

    @Override
    public String getHeader(String s) {
        return headers.get(s);
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
    public HttpServerRequest bodyHandler(Handler<Buffer> bodyHandler) {
        this.bodyHandler = bodyHandler;
        return this;
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

    // custom stuff..

    public HttpServerRequest end(Buffer buffer) {
        if (handler != null){
            handler.handle(null);
        }
        if (endHandler != null){
            endHandler.handle(null);
        }
        return this;
    }

    public HttpServerRequest setHeader(String key, String value) {
        headers.add(key, value);
        return this;
    }
}