package com.spriet2000.vertx.http.api.binders.value.impl;

import com.spriet2000.vertx.http.api.binders.method.MethodInfo;
import com.spriet2000.vertx.http.api.binders.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binders.value.Value;
import com.spriet2000.vertx.http.api.binders.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;

import static io.vertx.core.http.HttpHeaders.COOKIE;

public class CookieValueBinder implements ValueBinder {

    @Override
    public Value bind(RouteContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        String header = context.request().headers().get(COOKIE);
        if (header != null) {
            for (io.netty.handler.codec.http.cookie.Cookie cookie : ServerCookieDecoder.STRICT.decode(header)) {
                if (cookie.name().equalsIgnoreCase(parameterInfo.name())) {
                    return new Value(cookie.value(), parameterInfo.parameter().getType());
                }
            }
        }
        return null;
    }
}
