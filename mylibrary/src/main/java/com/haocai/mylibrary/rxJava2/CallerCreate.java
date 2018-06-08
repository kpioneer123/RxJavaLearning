package com.haocai.mylibrary.rxJava2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Xionghu on 2018/6/7.
 * Desc:
 */

public class CallerCreate<T> extends Caller<T> {

    private CallerOnCall<T> mCallerOnCall;

    public CallerCreate(CallerOnCall<T> mCallerOnCall) {
        this.mCallerOnCall = mCallerOnCall;
    }

    @Override
    protected void callActual(Callee<T> callee) {
        CreateEmitter<T> tCallerEmitter = new CreateEmitter<>(callee);
        callee.onCall(tCallerEmitter);
        mCallerOnCall.call(tCallerEmitter);

    }

    public static final class CreateEmitter<T> extends AtomicReference<Release> implements CallerEmitter<T>, Release {


        private Callee<T> mCallee;

        public CreateEmitter(Callee<T> mCallee) {
            this.mCallee = mCallee;
        }

        @Override
        public void onReceive(T t) {
            if (!isReleased()) {
                mCallee.onReceive(t);
            }
        }

        @Override
        public void onCompleted() {
            if (!isReleased()) {
                mCallee.onCompleted();
            }
        }

        @Override
        public void onError(Throwable t) {
            if (!isReleased()) {
                mCallee.onError(t);
            }
        }

        @Override
        public boolean isReleased() {
            return ReleaseHelper.isReleased(get());
        }

        @Override
        public void release() {
            ReleaseHelper.release(this);
        }
    }
}
