package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/7.
 * Desc:
 */

public abstract class Caller<T> {
    public static <T> Caller<T> create(CallerOnCall<T> callerOnCall) {
        return new CallerCreate<>(callerOnCall);
    }

    public void call(Callee<T> callee) {
        callActual(callee);
    }

    protected abstract void callActual(Callee<T> callee);
}
