package vertx.handlers.http.api.test.ext.bodyParser;

public interface Body<T> {
    void body(T body);

    T body();
}
