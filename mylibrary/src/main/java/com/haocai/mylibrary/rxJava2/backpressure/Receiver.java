package com.haocai.mylibrary.rxJava2.backpressure;

/**
 * Created by Xionghu on 2018/6/8.
 * Desc:
 */

public interface Receiver<T> {

    void onCall(Drop d);

    void onReceive(T t);

    void onError(Throwable t);

    void onCompleted();
}
