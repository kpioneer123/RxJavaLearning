package com.haocai.mylibrary.rxJava2;



/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 新线程的switcher
 */

public class NewThreadSwitcher extends Switcher {
    @Override
    public Worker createWorker() {
        return new NewThreadWorker();
    }
}
