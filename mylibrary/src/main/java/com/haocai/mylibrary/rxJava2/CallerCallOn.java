package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/15.
 * Desc: 用于callOn
 */

public class CallerCallOn<T> extends CallerWithUpstream<T, T> {
    private Switcher mSwitcher;

    public CallerCallOn(Caller<T> source, Switcher mSwitcher) {
        super(source);
        this.mSwitcher = mSwitcher;
    }

    @Override
    protected void callActual(Callee<T> callee) {
        final CallOnCallee<T> tCallOnCallee = new CallOnCallee<>(callee);
        callee.onCall(tCallOnCallee);
        mSwitcher.switches(new Runnable() {
            @Override
            public void run() {
                source.call(tCallOnCallee);
            }
        });
    }

    private static final class CallOnCallee<T> implements Callee<T>, Release {

        private final Callee<T> callee;

        public CallOnCallee(Callee<T> callee) {
            this.callee = callee;
        }

        @Override
        public void onCall(Release release) {

        }

        @Override
        public void onReceive(T t) {
            callee.onReceive(t);
        }

        @Override
        public void onCompleted() {
            callee.onCompleted();
        }

        @Override
        public void onError(Throwable t) {
            callee.onError(t);
        }

        @Override
        public boolean isReleased() {
            return false;
        }

        @Override
        public void release() {

        }
    }
}
