package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/7.
 * Desc: 当打电话的时候
 */

public interface CallerOnCall<T> {
    void call(CallerEmitter<T> callerEmitter);
}
