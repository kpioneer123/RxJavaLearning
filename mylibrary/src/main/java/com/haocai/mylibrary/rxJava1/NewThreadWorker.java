package com.haocai.mylibrary.rxJava1;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc:新线程的工作类
 */

public class NewThreadWorker extends Switcher.Worker {

    //newScheduledThreadPool :创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
    private final ExecutorService mExecutor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, " NewThreadWorker");
        }
    });
    private volatile boolean mIsUnCalled;

    @Override
    public void unCall() {
        mIsUnCalled = true;
    }

    @Override
    public boolean isUnCalled() {
        return mIsUnCalled;
    }

    @Override
    public Calling switches(Action0 action0) {
        SwitcherAction switcherAction = new SwitcherAction(action0);
        mExecutor.submit(switcherAction);
        return switcherAction;
    }

    private static class SwitcherAction implements Runnable, Calling {
        private final Action0 action0;
        private volatile boolean mIsUnCalled;

        public SwitcherAction(Action0 action0) {
            this.action0 = action0;
        }

        @Override
        public void unCall() {
            mIsUnCalled = true;
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
