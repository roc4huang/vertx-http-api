package com.spriet2000.vertx.http.api.binders.value.impl;

import com.spriet2000.vertx.http.api.binders.method.MethodInfo;
import com.spriet2000.vertx.http.api.binders.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binders.value.Value;
import com.spriet2000.vertx.http.api.binders.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class QueryValueBinder implements ValueBinder {

    @Override
    public Value bind(RouteContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        final String[] pairs = context.request().query().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            try {
                final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                if (key.equalsIgnoreCase(parameterInfo.name())) {
                    final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                    return new Value(value, parameterInfo.parameter().getType());
                }
            } catch (UnsupportedEncodingException ignored) {

            }
        }
        return null;
    }
}
