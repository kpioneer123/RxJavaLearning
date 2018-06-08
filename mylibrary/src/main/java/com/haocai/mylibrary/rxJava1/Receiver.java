package com.haocai.mylibrary.rxJava1;

/**
 * Created by Xionghu on 2018/6/6.
 * Desc: 接收信息的人
 */

public abstract class Receiver<T> implements Callee<T>, Calling {

    private volatile boolean unCalled;

    @Override
    public void unCall() {
        unCalled = true;
    }

    @Override
    public boolean isUnCalled() {
        return unCalled;
    }
}
