package vertx.handlers.http.api.ext.impl;


import vertx.handlers.http.api.ext.Result;

public class ErrorContext implements Result {

    private final Integer code;
    private final Exception exception;

    public ErrorContext(Exception exception) {

        this.exception = exception;
        code = null;
    }

    public ErrorContext(Integer code) {

        this.code = code;
        exception = null;
    }

    public Object result() {
        if (code != null) {
            return code();
        }
        if (exception() != null) {
            return exception();
        }
        return new Exception();
    }

    public Exception exception() {
        return exception;
    }

    public Integer code() {
        return code == null ? Integer.valueOf(500) : code;
    }
}
