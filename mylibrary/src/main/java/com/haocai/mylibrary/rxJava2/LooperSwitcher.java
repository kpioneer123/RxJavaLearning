package com.haocai.mylibrary.rxJava2;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: Android 中切换到某个线程的Looper中
 */

public class LooperSwitcher extends Switcher {
    private Handler mHandler;

    public LooperSwitcher(Looper looper) {
        mHandler = new Handler(looper);
    }

    public Release switches(final Runnable runnable) {
        SwitcherRunnable switcherRunnable = new SwitcherRunnable(runnable, mHandler);
        mHandler.post(switcherRunnable);
        return switcherRunnable;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(mHandler);
    }

    public static class SwitcherRunnable implements Runnable, Release {

        private final Runnable mRunnable;
        private final Handler mHandler;
        private volatile boolean mIsReleased;

        public SwitcherRunnable(Runnable mRunnable, Handler mHandler) {
            this.mRunnable = mRunnable;
            this.mHandler = mHandler;
        }

        @Override
        public boolean isReleased() {
            return mIsReleased;
        }

        @Override
        public void release() {
            mIsReleased = true;
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            mRunnable.run();
        }
    }
}
