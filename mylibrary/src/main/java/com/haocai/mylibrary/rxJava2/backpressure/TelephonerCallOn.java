package com.haocai.mylibrary.rxJava2.backpressure;

import com.haocai.mylibrary.rxJava2.Switcher;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Xionghu on 2018/6/15.
 * Desc:用于callOn
 */

public class TelephonerCallOn<T> extends TelephonerWithUpstream<T, T> {

    private final Switcher mSwitcher;

    public TelephonerCallOn(Telephoner<T> source, Switcher switcher) {
        super(source);
        mSwitcher = switcher;
    }

    @Override
    protected void callActual(Receiver<T> receiver) {
        final CallOnReceiver<T> tCallOnReceiver = new CallOnReceiver<>(receiver);
        receiver.onCall(tCallOnReceiver);
        mSwitcher.switches(new Runnable() {
            @Override
            public void run() {
                source.call(tCallOnReceiver);
            }
        });

    }

    private static final class CallOnReceiver<T> extends AtomicLong implements Receiver<T>, Drop {

        private final Receiver<T> mReceiver;

        public CallOnReceiver(Receiver<T> receiver) {
            mReceiver = receiver;
        }

        @Override
        public void request(long n) {
            BackpressureHelper.add(this, n);
        }

        @Override
        public void drop() {

        }

        @Override
        public void onCall(Drop d) {
            mReceiver.onCall(d);
        }

        @Override
        public void onReceive(T t) {
            if (get() != 0) {
                mReceiver.onReceive(t);
                BackpressureHelper.produced(this, 1);
            }
        }

        @Override
        public void onError(Throwable t) {
            mReceiver.onError(t);
        }

        @Override
        public void onCompleted() {
            mReceiver.onCompleted();
        }
    }
}
