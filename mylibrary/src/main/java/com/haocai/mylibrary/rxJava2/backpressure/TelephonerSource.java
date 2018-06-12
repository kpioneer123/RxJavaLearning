package com.haocai.mylibrary.rxJava2.backpressure;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc: 返回源Telephoner
 */

public interface TelephonerSource<T> {
    Telephoner<T> source();
}
