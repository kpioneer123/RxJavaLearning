package com.haocai.mylibrary.rxJava2.backpressure;

/**
 * Created by Xionghu on 2018/6/8.
 * Desc:
 */

public abstract class Telephoner<T> {
    public static <T> Telephoner<T> create(TelephonerOnCall<T> telephonerOnCall){
        return new TelephonerCreate<>(telephonerOnCall);
        }

    public void call(Receiver<T> receiver) { callActual(receiver);}

    protected abstract void callActual(Receiver<T> receiver);
}
