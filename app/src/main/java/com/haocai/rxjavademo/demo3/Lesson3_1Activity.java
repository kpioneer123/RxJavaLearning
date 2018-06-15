package com.haocai.rxjavademo.demo3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.haocai.rxjavademo.R;



//RxJava1
//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;

//RxJava2
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Lesson3_1Activity extends AppCompatActivity {

    public static final String TAG = "kpioneer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btnRxJava1, R.id.btnRxJava2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRxJava1://使用RxJava1库
//                Observable.
//                        create(new Observable.OnSubscribe<String>() {
//                            @Override
//                            public void call(Subscriber<? super String> subscriber) {
//                                if (!subscriber.isUnsubscribed()) {
//                                    Log.d(TAG, "currentThread:" + Thread.currentThread());
//                                    subscriber.onNext("test");
//                                    subscriber.onCompleted();
//                                }
//                            }
//                        }).
//                        subscribeOn(Schedulers.newThread()).
//                        observeOn(AndroidSchedulers.mainThread()).
//                        subscribe(new Observer<String>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(String s) {
//                                Log.d(TAG, "onNext:" + s + "currentThread:" + Thread.currentThread());
//                            }
//                        });

                break;
            case R.id.btnRxJava2://使用RxJava2库
                   /*---------无背压---------*/
                Observable.
                        create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                if (!emitter.isDisposed()) {
                                    Log.d(TAG, "Observable currentThread:" + Thread.currentThread());
                                    emitter.onNext("test");
                                    emitter.onComplete();
                                }
                            }
                        }).
                        subscribeOn(Schedulers.newThread()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String o) {
                                Log.d(TAG, "Observable onNext:" + o);
                                Log.d(TAG, "Observable currentThread:" + Thread.currentThread());

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                  /*---------有背压---------*/
                Flowable.create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                        if (!emitter.isCancelled()) {
                            Log.d(TAG, "Flowable currentThread:" + Thread.currentThread());
                            emitter.onNext("test");
                            emitter.onComplete();
                        }
                    }
                }, BackpressureStrategy.DROP).
                        subscribeOn(Schedulers.newThread()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new Subscriber<String>() {
                            @Override
                            public void onSubscribe(Subscription s) {
                                s.request(Long.MAX_VALUE);
                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "Flowable onNext:" + s);
                                Log.d(TAG, "Flowable currentThread:" + Thread.currentThread());
                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
        }
    }
}
