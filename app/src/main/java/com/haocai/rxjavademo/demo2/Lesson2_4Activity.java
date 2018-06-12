package com.haocai.rxjavademo.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava2.Function;
import com.haocai.mylibrary.rxJava2.backpressure.Drop;
import com.haocai.mylibrary.rxJava2.backpressure.Receiver;
import com.haocai.mylibrary.rxJava2.backpressure.Telephoner;
import com.haocai.mylibrary.rxJava2.backpressure.TelephonerEmitter;
import com.haocai.mylibrary.rxJava2.backpressure.TelephonerOnCall;
import com.haocai.mylibrary.rxJava2.backpressure.TelephonerOperator;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc: 仿写RxJava2 无背压 操作符方法
 */

public class Lesson2_4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.testDo)
    public void onViewClicked() {

        Telephoner.
                create(new TelephonerOnCall<String>() {
                    @Override
                    public void call(TelephonerEmitter<String> telephonerEmitter) {
                        telephonerEmitter.onReceive("1");
                        telephonerEmitter.onReceive("2");
                        telephonerEmitter.onCompleted();
                    }
                }).
                map(new Function<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                }).
                call(new Receiver<Integer>() {
                    @Override
                    public void onCall(Drop d) {
                        d.request(Long.MAX_VALUE);
                        Log.d("kpioneer", "onCall");
                    }

                    @Override
                    public void onReceive(Integer integer) {
                        Log.d("kpioneer", "onReceive:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                        Log.d("kpioneer", "onError");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("kpioneer", "onCompleted");
                    }
                });

        Telephoner.
                create(new TelephonerOnCall<String>() {
                    @Override
                    public void call(TelephonerEmitter<String> telephonerEmitter) {
                        telephonerEmitter.onReceive("3");
                        telephonerEmitter.onReceive("4");
                        telephonerEmitter.onCompleted();
                    }
                }).
                lift(new TelephonerOperator<Integer, String>() {
                    @Override
                    public Receiver<String> call(final Receiver<Integer> receiver) {
                        return new Receiver<String>() {
                            @Override
                            public void onCall(Drop d) {
                                receiver.onCall(d);
                            }

                            @Override
                            public void onReceive(String s) {
                                receiver.onReceive(Integer.parseInt(s));
                            }

                            @Override
                            public void onError(Throwable t) {
                                receiver.onError(t);
                            }

                            @Override
                            public void onCompleted() {
                                receiver.onCompleted();
                            }
                        };
                    }
                }).
                call(new Receiver<Integer>() {
                    @Override
                    public void onCall(Drop d) {
                        d.request(Long.MAX_VALUE);
                        Log.d("kpioneer", "onCall");
                    }

                    @Override
                    public void onReceive(Integer integer) {
                        Log.d("kpioneer", "onReceive:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("kpioneer", "onError");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("kpioneer", "onCompleted");
                    }
                });


    }
}
