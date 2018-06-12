package com.haocai.mylibrary.rxJava2.backpressure;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc:
 */

public abstract class TelephonerWithUpstream<T, R> extends Telephoner<R> implements TelephonerSource {
    protected final Telephoner<T> source;

    public TelephonerWithUpstream(Telephoner<T> source) {
        this.source = source;
    }

    @Override
    public Telephoner source() {
        return source;
    }

}
