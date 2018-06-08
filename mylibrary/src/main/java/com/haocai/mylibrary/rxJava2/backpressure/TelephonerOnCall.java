package com.haocai.mylibrary.rxJava2.backpressure;

/**
 * Created by Xionghu on 2018/6/8.
 * Desc:
 */

public interface TelephonerOnCall<T>{
    void call(TelephonerEmitter<T> telephonerEmitter);
}
