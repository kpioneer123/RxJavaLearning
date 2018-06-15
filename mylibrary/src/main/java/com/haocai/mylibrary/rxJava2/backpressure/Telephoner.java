package com.haocai.mylibrary.rxJava2.backpressure;

import com.haocai.mylibrary.rxJava2.Function;
import com.haocai.mylibrary.rxJava2.Switcher;

/**
 * Created by Xionghu on 2018/6/8.
 * Desc:
 */

public abstract class Telephoner<T> {
    public static <T> Telephoner<T> create(TelephonerOnCall<T> telephonerOnCall) {
        return new TelephonerCreate<>(telephonerOnCall);
    }

    public void call(Receiver<T> receiver) {
        callActual(receiver);
    }

    protected abstract void callActual(Receiver<T> receiver);

    public <R> Telephoner<R> map(Function<T, R> function) {
        return new TelephonerMap<>(this, function);
    }

    public <R> Telephoner<R> lift(TelephonerOperator<R, T> telephonerOperator) {
        return new TelephonerLift<>(this, telephonerOperator);
    }

    public Telephoner<T> callOn(Switcher switcher) {
        return new TelephonerCallOn<>(this, switcher);
    }
}
