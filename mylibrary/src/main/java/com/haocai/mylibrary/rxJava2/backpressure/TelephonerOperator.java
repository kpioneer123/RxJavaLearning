package com.haocai.mylibrary.rxJava2.backpressure;

/**
 * Created by Xionghu on 2018/6/12.
 * Desc: 操作符接口
 */

public interface TelephonerOperator<T, R> {
    Receiver<R> call(Receiver<T> callee);
}
