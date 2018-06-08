package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/7.
 * Desc:
 */

public interface Emitter<T> {
    void onReceive(T t);

    void onCompleted();

    void onError(Throwable t);
}
