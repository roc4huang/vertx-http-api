package com.spriet2000.vertx.http.api.binding.data.imp;


import com.spriet2000.vertx.http.api.binding.data.DataBinder;
import com.spriet2000.vertx.http.api.binding.parameter.ParameterInfo;
import com.spriet2000.vertx.http.api.routing.impl.RouteContext;

public class DefaultDataBinder implements DataBinder {

    private final DataBinder queryValueBinder = new QueryDataBinder();
    private final DataBinder cookieValueBinder = new CookieDataBinder();

    @Override
    public void bind(RouteContext context, ParameterInfo parameterInfo) {
        switch (parameterInfo.getFrom()) {
            case Query:
                queryValueBinder.bind(context, parameterInfo);
            case Cookie:
                cookieValueBinder.bind(context, parameterInfo);
        }
    }
}
