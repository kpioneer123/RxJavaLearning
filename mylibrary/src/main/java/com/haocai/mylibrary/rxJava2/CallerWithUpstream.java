package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc:
 */

public abstract class CallerWithUpstream<T, R> extends Caller<R> implements CallerSource<T> {
    protected final Caller<T> source;

    public CallerWithUpstream(Caller<T> source) {
        this.source = source;
    }

    @Override
    public Caller<T> source() {
        return source;
    }

}
