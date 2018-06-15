package com.haocai.mylibrary.rxJava1;

import android.support.annotation.WorkerThread;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 用于线程切换
 */

public abstract  class Switcher {
    public abstract Worker createWorker();

    public static abstract class Worker implements Calling{
        public abstract Calling switches(Action0 action0);
    }
}
