package com.haocai.rxjavademo.demo2;

//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//
//import com.haocai.rxjavademo.R;
//
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.functions.Func1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.haocai.rxjavademo.R;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


public class Lesson2_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2_1);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btnRxJava1, R.id.btnRxJava2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRxJava1://使用RxJava1库
//                Observable.create(new Observable.OnSubscribe<String>() {
//                            @Override
//                            public void call(Subscriber<? super String> subscriber) {
//                                if (!subscriber.isUnsubscribed()) {
//                                    subscriber.onNext("1");
//                                    subscriber.onNext("2");
//                                    subscriber.onCompleted();
//                                }
//                            }
//                        }).
//                        //处理
//                        map(new Func1<String, Integer>() {
//                            @Override
//                            public Integer call(String s) {
//                                return Integer.parseInt(s)+2;
//                            }
//                        }).
//                        subscribe(new Observer<Integer>() {
//                            @Override
//                            public void onCompleted() {
//                                Log.d("kpioneer", "onCompleted:");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(Integer integer) {
//                                Log.d("kpioneer", "onNext:" + integer + ",integer instanceOf" + integer.getClass());
//                            }
//                        });
                break;
            case R.id.btnRxJava2://使用RxJava2库
                Observable.
                        create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> e) throws Exception {
                                if (!e.isDisposed()) {
                                    e.onNext("1");
                                    e.onNext("2");
                                    e.onComplete();
                                }
                            }
                        }).
                        map(new Function<String, Integer>() {
                            @Override
                            public Integer apply(String s) throws Exception {
                                return Integer.parseInt(s)+2;
                            }
                        }).
                        subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d("kpioneer", "onSubscribe:");
                            }

                            @Override
                            public void onNext(Integer value) {
                                Log.d("kpioneer", "onNext:" + value);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d("kpioneer", "onComplete" );
                            }
                        });

                Flowable.
                        create(new FlowableOnSubscribe<String>() {
                            @Override
                            public void subscribe(FlowableEmitter<String> e) throws Exception {
                                if (!e.isCancelled()) {
                                    e.onNext("1");
                                    e.onNext("2");
                                    e.onComplete();
                                }
                            }
                        }, BackpressureStrategy.DROP).
                        map(new Function<String, Integer>() {
                            @Override
                            public Integer apply(String s) throws Exception {
                                return Integer.parseInt(s)+2;
                            }
                        }).
                        subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription s) {
                                s.request(Long.MAX_VALUE);
                                Log.d("kpioneer", "onSubscribe");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.d("kpioneer", "onNext:" + integer);
                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d("kpioneer", "onComplete");
                            }
                        });
                break;
        }
    }
}
