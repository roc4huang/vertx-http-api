package vertx.handlers.http.api.binding;

import io.vertx.core.Future;
import io.vertx.core.Handler;

public interface BindingContext {
    void add(Future<Object> future);

    void completeHandler(Handler<Object[]> completeHandler);
}
