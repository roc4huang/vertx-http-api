package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.RoutingContext;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;

import static io.vertx.core.http.HttpHeaders.COOKIE;

public class CookieValue implements ValueBinder {

    @Override
    public Value bind(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        String header = context.request().headers().get(COOKIE);
        if (header != null) {
            for (io.netty.handler.codec.http.cookie.Cookie cookie : ServerCookieDecoder.STRICT.decode(header)) {
                if (cookie.name().equalsIgnoreCase(parameterInfo.name())) {
                    return new Value(cookie.value(), String.class);
                }
            }
        }
        return null;
    }
}
