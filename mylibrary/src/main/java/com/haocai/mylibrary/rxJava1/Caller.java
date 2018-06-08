package com.haocai.mylibrary.rxJava1;


/**
 * Created by Xionghu on 2018/6/6.
 * Desc:
 */

public class Caller<T> {
    final OnCall<T> onCall;

    public Caller(OnCall<T> onCall) {
        this.onCall = onCall;
    }

    public static <T> Caller<T> create(OnCall<T> onCall) {
        return new Caller<>(onCall);
    }

    public Calling call(Receiver<T> receiver) {
        this.onCall.call(receiver);
        return receiver;
    }

    public interface OnCall<T> extends Action1<Receiver<T>> {

    }

}
