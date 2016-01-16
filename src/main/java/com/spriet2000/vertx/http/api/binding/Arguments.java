package com.spriet2000.vertx.http.api.binding;

public class Arguments {

    private Object[] arguments;
    private int length;

    public Arguments(int length){
        this.length = length;
        arguments = new Object[length];
    }

    public Object get(int i) {
        return arguments[i];
    }

    public void set(int i, Object value){
        arguments[i] = value;
    }

    public int length() {
        return length;
    }
}
