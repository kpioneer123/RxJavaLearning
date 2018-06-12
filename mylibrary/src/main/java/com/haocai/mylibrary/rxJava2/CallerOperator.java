package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc:
 */

public interface CallerOperator<T,R> {
    Callee<R> call(Callee<T> callee);
}
