package com.haocai.mylibrary.rxJava2;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 用于Android的Worker
 */

public class HandlerWorker extends Switcher.Worker {

    private final Handler mHandler;

    private volatile boolean mIsReleased;

    public HandlerWorker(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public boolean isReleased() {
        return mIsReleased;
    }

    @Override
    public void release() {
        mIsReleased = true;
        mHandler.removeCallbacksAndMessages(this);
    }

    @Override
    public Release switches(Runnable runnable) {
        LooperSwitcher.SwitcherRunnable switcherRunnable = new LooperSwitcher.SwitcherRunnable(runnable,mHandler);
        Message tMessage = Message.obtain(mHandler,switcherRunnable);
        tMessage.obj = this;
        mHandler.sendMessage(tMessage);
        return switcherRunnable;
    }
}
