package com.haocai.mylibrary.rxJava2;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc:新线程的工作类
 */

public class NewThreadWorker extends Switcher.Worker {
    private final ExecutorService mExecutor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "NewThreadWorker");
        }
    });
    private volatile boolean mIsReleased;

    @Override
    public boolean isReleased() {
        return mIsReleased;
    }

    @Override
    public void release() {
        mIsReleased = true;
    }

    @Override
    public Release switches(Runnable runnable) {
        SwitcherAction switcherAction = new SwitcherAction(runnable);
        mExecutor.submit((Callable<Object>) switcherAction);
        return switcherAction;
    }

    private static class SwitcherAction implements Runnable, Callable<Object>, Release {

        private final Runnable mRunnable;

        private volatile boolean mIsReleased;

        public SwitcherAction(Runnable mRunnable) {
            this.mRunnable = mRunnable;
        }

        @Override
        public boolean isReleased() {
            return mIsReleased;
        }

        @Override
        public void release() {
            mIsReleased = true;
        }

        @Override
        public void run() {
            mRunnable.run();
        }

        @Override
        public Object call() throws Exception {
            run();
            return null;
        }
    }
}
