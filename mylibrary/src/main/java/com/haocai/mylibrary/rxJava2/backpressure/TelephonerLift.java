package com.haocai.mylibrary.rxJava2.backpressure;

/**
 * Created by Xionghu on 2018/6/12.
 * Desc: lift操作符
 */

public class TelephonerLift<R, T> extends TelephonerWithUpstream<T, R> {


    private final TelephonerOperator<R, T> operator;

    public TelephonerLift(Telephoner<T> source, TelephonerOperator<R, T> operator) {
        super(source);
        this.operator = operator;
    }

    @Override
    protected void callActual(Receiver<R> receiver) {
        Receiver<T> tReceiver = operator.call(receiver);
        source.call(tReceiver);
    }
}
