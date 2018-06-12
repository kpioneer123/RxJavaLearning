package com.haocai.mylibrary.rxJava2;


/**
 * Created by Xionghu on 2018/6/11.
 * Desc:
 */

public class CallerMap<T, R> extends CallerWithUpstream<T, R> {
    private Function<T, R> function;

    public CallerMap(Caller<T> source, Function<T, R> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void callActual(Callee<R> callee) {
        source.call(new MapCallee<>(callee, function));
    }

    static class MapCallee<T, R> implements Callee<T> {
        private final Callee<R> mCallee;
        private final Function<T, R> mFunction;
        public MapCallee(Callee<R> mCallee, Function<T, R> mFunction) {
            this.mCallee = mCallee;
            this.mFunction = mFunction;
        }

        @Override
        public void onCall(Release release) {
            mCallee.onCall(release);
        }

        @Override
        public void onReceive(T t) {
            R tR = mFunction.call(t);
            mCallee.onReceive(tR);
        }

        @Override
        public void onCompleted() {
            mCallee.onCompleted();
        }

        @Override
        public void onError(Throwable t) {
            mCallee.onError(t);
        }
    }
}
