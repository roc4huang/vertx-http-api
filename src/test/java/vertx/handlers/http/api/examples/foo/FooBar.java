package vertx.handlers.http.api.examples.foo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FooBar {
    public final String foo;

    public FooBar(@JsonProperty("examples") String foo) {
        this.foo = foo;
    }
}
