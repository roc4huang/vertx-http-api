package vertx.handlers.http.api.binding.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import vertx.handlers.http.api.binding.BindingContext;

import java.util.ArrayList;
import java.util.List;

public class DefaultBindingContext implements BindingContext {

    private final List<Future<Object>> list = new ArrayList<>();

    private final List<Object> results = new ArrayList<>();
    private final int parameterCount;

    private Handler<Object[]> completeHandler;

    public DefaultBindingContext(int parameterCount){

        this.parameterCount = parameterCount;
    }

    @Override
    public void add(Future<Object> future) {
        future.setHandler(f -> {
                    results.add(f.result());
                    if (results.size() == parameterCount) {
                        completeHandler.handle(results.toArray());
                    }
        });
        list.add(future);
    }

    @Override
    public void completeHandler(Handler<Object[]> completeHandler) {
        this.completeHandler = completeHandler;
    }
}