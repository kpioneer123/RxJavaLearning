package com.haocai.mylibrary.rxJava1;


import android.os.Handler;
import android.os.Message;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 用于Android 的Worker
 */

public class HandlerWorker extends Switcher.Worker {
    private final Handler mHandler;

    private volatile boolean mIsUnCalled;

    public HandlerWorker(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void unCall() {
        mIsUnCalled = true;
        mHandler.removeCallbacksAndMessages(this);
    }

    @Override
    public boolean isUnCalled() {
        return mIsUnCalled;
    }

    @Override
    public Calling switches(Action0 action0) {
        SwitcherAction switcherAction = new SwitcherAction(action0, mHandler);
        Message message = Message.obtain(mHandler, switcherAction);
        message.obj = this;
        mHandler.sendMessage(message);
        return switcherAction;
    }

    private static class SwitcherAction implements Runnable, Calling {
        private final Action0 action0;
        private final Handler handler;
        private volatile boolean mIsUnCalled;

        public SwitcherAction(Action0 action0, Handler handler) {
            this.action0 = action0;
            this.handler = handler;
        }

        @Override
        public void unCall() {
            mIsUnCalled = true;
            handler.removeCallbacks(this);
        }

        @Override
        public boolean isUnCalled() {
            return mIsUnCalled;
        }

        @Override
        public void run() {
            action0.call();
        }
    }
}
