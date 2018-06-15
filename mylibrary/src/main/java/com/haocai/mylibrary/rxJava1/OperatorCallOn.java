package com.haocai.mylibrary.rxJava1;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 用于callOn的OnCall
 */

public class OperatorCallOn<T> implements Caller.OnCall<T> {

    private final Switcher switcher;
    private final Caller<T> tCaller;

    public OperatorCallOn(Switcher switcher, Caller<T> tCaller) {
        this.switcher = switcher;
        this.tCaller = tCaller;
    }

    @Override
    public void call(final Receiver<T> tReceiver) {
        Switcher.Worker worker = switcher.createWorker();
        worker.switches(new Action0() {
            @Override
            public void call() {
                Receiver<T> tReceiver1 = new Receiver<T>() {
                    @Override
                    public void onCompleted() {
                        tReceiver.onCompleted();
                    }

                    @Override
                    public void onError(Throwable t) {
                        tReceiver.onError(t);
                    }

                    @Override
                    public void onReceive(T t) {
                        tReceiver.onReceive(t);
                    }
                };
                tCaller.call(tReceiver1);
            }
        });
    }
}
