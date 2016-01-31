package com.spriet2000.vertx.http.api.binding.data.imp;

import com.spriet2000.vertx.http.api.binding.data.DataBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;

import static io.vertx.core.http.HttpHeaders.COOKIE;

public class CookieDataBinder implements DataBinder {

    @Override
    public void bind(RouteContext context, ParameterInfo parameterInfo) {
        String header = context.request().headers().get(COOKIE);
        if (header != null) {
            for (io.netty.handler.codec.http.cookie.Cookie cookie : ServerCookieDecoder.STRICT.decode(header)) {
                if (cookie.name().equalsIgnoreCase(parameterInfo.name())) {
                    context.data().add(cookie.name(), cookie.value());
                }
            }
        }
    }
}
