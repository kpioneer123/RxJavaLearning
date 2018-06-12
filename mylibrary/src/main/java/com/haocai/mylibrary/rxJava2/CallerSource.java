package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc:返回源Caller
 */

public interface CallerSource<T> {
    Caller<T> source();
}
