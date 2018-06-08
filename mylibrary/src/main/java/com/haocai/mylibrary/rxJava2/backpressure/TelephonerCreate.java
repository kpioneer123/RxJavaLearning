package com.haocai.mylibrary.rxJava2.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Xionghu on 2018/6/8.
 * Desc:
 */

public class TelephonerCreate<T> extends Telephoner<T> {
    private TelephonerOnCall<T> mTelephonerOnCall;

    public TelephonerCreate(TelephonerOnCall<T> telephonerOnCall) {
        mTelephonerOnCall = telephonerOnCall;
    }

    @Override
    protected void callActual(Receiver<T> receiver) {
        DropEmitter<T> tDropEmitter = new DropEmitter<>(receiver);
        receiver.onCall(tDropEmitter);
        mTelephonerOnCall.call(tDropEmitter);
    }

    private static class DropEmitter<T>
            extends AtomicLong
            implements TelephonerEmitter<T>, Drop {

        private Receiver<T> mReceiver;

        private volatile boolean mIsDropped;

        private DropEmitter(Receiver<T> receiver) {
            mReceiver = receiver;
        }

        @Override
        public void onReceive(T t) {
            if (get() != 0) {
                mReceiver.onReceive(t);
                BackpressureHelper.produced(this, 1); //消耗事件  直到为0
            }
        }

        @Override
        public void request(long n) {
            BackpressureHelper.add(this, n); //n表示数据的数量 添加事件
        }

        @Override
        public void onCompleted() {
            mReceiver.onCompleted();
        }

        @Override
        public void drop() {
            mIsDropped = true;
        }

        @Override
        public void onError(Throwable t) {
            mReceiver.onError(t);
        }
    }
}
