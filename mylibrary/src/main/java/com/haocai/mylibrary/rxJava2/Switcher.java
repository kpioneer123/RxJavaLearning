package com.haocai.mylibrary.rxJava2;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 用于线程切换的抽象类
 */

public abstract class Switcher {

    public abstract Worker createWorker();

    public Release switches(final Runnable runnable) {
        Worker worker = createWorker();
        worker.switches(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
        return worker;
    }

    public static abstract class Worker implements Release {
        public abstract Release switches(Runnable runnable);
    }
}
