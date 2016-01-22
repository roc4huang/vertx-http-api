package com.spriet2000.vertx.http.api.binding.impl;

import com.spriet2000.vertx.http.api.binding.MethodInfo;
import com.spriet2000.vertx.http.api.binding.ParameterInfo;
import com.spriet2000.vertx.http.api.binding.ParameterValue;
import com.spriet2000.vertx.http.api.routing.RoutingContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class QueryValue implements ParameterValue {

    @Override
    public Object get(RoutingContext context, MethodInfo methodInfo, ParameterInfo parameterInfo) {
        final String[] pairs = context.httpServerRequest().query().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            try {
                final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                if(key.equalsIgnoreCase(parameterInfo.name())){
                    return idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                }
            } catch (UnsupportedEncodingException ignored) {

            }
        }
        return null;
    }
}
