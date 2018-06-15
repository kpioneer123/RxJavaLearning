package com.haocai.mylibrary.rxJava1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Xionghu on 2018/6/14.
 * Desc: 用于CallbackOn
 */

public class OperatorCallbackOn<T> implements Caller.Operator<T, T> {

    private final Switcher switcher;

    public OperatorCallbackOn(Switcher switcher) {
        this.switcher = switcher;
    }

    @Override
    public Receiver<T> call(final Receiver<T> tReceiver) {

        return new CallbackOnReceiver<>(tReceiver, switcher);
    }

    private static final class CallbackOnReceiver<T> extends Receiver<T> implements Action0 {

        private final Receiver<T> tReceiver;
        private final Switcher.Worker worker;
        private final Queue<T> tQueue = new LinkedList<>();

        public CallbackOnReceiver(Receiver<T> tReceiver, Switcher switcher) {
            this.tReceiver = tReceiver;
            this.worker = switcher.createWorker();
        }

        @Override
        public void call() {
            T t = tQueue.poll(); //移除元素，如果队列为空，则返回null
            tReceiver.onReceive(t);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable t) {
        }

        @Override
        public void onReceive(T t) {
            tQueue.offer(t);// offer：添加一个元素并返回true        如果队列已满，则返回false
            switches();
        }

        private void switches() {
            worker.switches(this);
        }
    }
}
