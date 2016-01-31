package com.spriet2000.vertx.http.api.binding.data.imp;

import com.spriet2000.vertx.http.api.binding.data.DataBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class QueryDataBinder implements DataBinder {

    @Override
    public void bind(RouteContext context, ParameterInfo parameterInfo) {
        final String[] pairs = context.request().query().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            try {
                final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                if (key.equalsIgnoreCase(parameterInfo.name())) {
                    final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                    context.data().add(key, value);
                }
            } catch (UnsupportedEncodingException ignored) {

            }
        }
    }
}