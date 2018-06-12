package com.haocai.mylibrary.rxJava1;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc:
 */

public class OnCallLift<T, R> implements Caller.OnCall<R> {

    private final Caller.OnCall<T> parent;

    private final Caller.Operator<R, T> operator;

    public OnCallLift(Caller.OnCall<T> parent, Caller.Operator<R, T> operator) {
        this.parent = parent;
        this.operator = operator;
    }

    @Override
    public void call(Receiver<R> rReceiver) {
        Receiver<T> tReceiver = this.operator.call(rReceiver);
        this.parent.call(tReceiver);
    }

}
