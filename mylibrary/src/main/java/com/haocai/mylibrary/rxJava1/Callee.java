package com.haocai.mylibrary.rxJava1;

/**
 * Created by Xionghu on 2018/6/6.
 * Desc:
 */

public interface Callee<T>{
    void onCompleted();

    void onError(Throwable t);

    void onReceive(T t);
}
