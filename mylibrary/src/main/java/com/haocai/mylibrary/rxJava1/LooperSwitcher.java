package com.haocai.mylibrary.rxJava1;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 用于Android中Looper的Switcher
 */

public class LooperSwitcher extends Switcher {

    private Handler mHandler;

    public LooperSwitcher(Looper looper) {
        mHandler = new Handler(looper);
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(mHandler);
    }
}
