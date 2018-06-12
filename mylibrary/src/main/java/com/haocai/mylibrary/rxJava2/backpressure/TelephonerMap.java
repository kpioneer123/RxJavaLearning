package com.haocai.mylibrary.rxJava2.backpressure;

import com.haocai.mylibrary.rxJava2.Function;

/**
 * Created by Xionghu on 2018/6/12.
 * Desc: map操作符
 */

public class TelephonerMap<T, R> extends TelephonerWithUpstream<T, R> {

    private Function<T, R> trFunction;

    public TelephonerMap(Telephoner<T> source, Function<T, R> trFunction) {
        super(source);
        this.trFunction = trFunction;
    }

    @Override
    protected void callActual(Receiver<R> receiver) {
        source.call(new MapReceiver<>(receiver, trFunction));
    }

    static class MapReceiver<T, R> implements Receiver<T> {

        private final Receiver<R> rReceiver;
        private final Function<T, R> trFunction;

        public MapReceiver(Receiver<R> rReceiver, Function<T, R> trFunction) {
            this.rReceiver = rReceiver;
            this.trFunction = trFunction;
        }

        @Override
        public void onCall(Drop d) {
            rReceiver.onCall(d);
        }

        @Override
        public void onReceive(T t) {
            R tr = trFunction.call(t);
            rReceiver.onReceive(tr);
        }

        @Override
        public void onError(Throwable t) {
            rReceiver.onError(t);
        }

        @Override
        public void onCompleted() {
            rReceiver.onCompleted();
        }
    }
}
