package com.spriet2000.vertx.http.api.binding.value.impl;

import com.spriet2000.vertx.http.api.binding.method.MethodInfo;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.value.Value;
import com.spriet2000.vertx.http.api.binding.value.ValueBinder;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class QueryValue implements ValueBinder {

    @Override
    public Value bind(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        final String[] pairs = context.request().query().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            try {
                final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                if (key.equalsIgnoreCase(parameterInfo.name())) {
                    final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                    return new Value(value, String.class);
                }
            } catch (UnsupportedEncodingException ignored) {

            }
        }
        return null;
    }
}
