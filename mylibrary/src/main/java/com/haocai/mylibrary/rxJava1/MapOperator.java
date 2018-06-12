package com.haocai.mylibrary.rxJava1;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc:
 */

public class MapOperator<T, R> implements Caller.Operator<R, T> {


    private final Func1<T, R> mapper;

    public MapOperator(Func1<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Receiver<T> call(Receiver<R> rReceiver) {
        return new MapReceiver<>(rReceiver, this.mapper);
    }
}
