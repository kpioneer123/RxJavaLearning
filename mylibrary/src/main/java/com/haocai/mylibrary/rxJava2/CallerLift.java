package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc:
 */

public class CallerLift<R, T> extends CallerWithUpstream<T, R> {
    private final CallerOperator<R, T> mOperator;

    public CallerLift(Caller<T> source, CallerOperator<R, T> mOperator) {
        super(source);
        this.mOperator = mOperator;
    }

    @Override
    protected void callActual(Callee<R> callee) {
        Callee<T> tCallee = mOperator.call(callee);
        source.call(tCallee);
    }
}
