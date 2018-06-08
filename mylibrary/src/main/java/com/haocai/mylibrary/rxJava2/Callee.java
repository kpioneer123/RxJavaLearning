package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/7.
 * Desc: 接电话的人
 */

public interface Callee<T> {
    void onCall(Release release);

    void onReceive(T t);

    void onCompleted();

    void onError(Throwable t);

}
