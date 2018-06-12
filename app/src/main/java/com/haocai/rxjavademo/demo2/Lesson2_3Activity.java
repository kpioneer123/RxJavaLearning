package com.haocai.rxjavademo.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava2.Callee;
import com.haocai.mylibrary.rxJava2.Caller;
import com.haocai.mylibrary.rxJava2.CallerEmitter;
import com.haocai.mylibrary.rxJava2.CallerOnCall;
import com.haocai.mylibrary.rxJava2.CallerOperator;
import com.haocai.mylibrary.rxJava2.Function;
import com.haocai.mylibrary.rxJava2.Release;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc: 仿写RxJava2 无背压 操作符方法
 */

public class Lesson2_3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.testDo)
    public void onViewClicked() {
        Caller.
                create(new CallerOnCall<String>() {
                    @Override
                    public void call(CallerEmitter<String> callerEmitter) {
                        callerEmitter.onReceive("1");
                        callerEmitter.onReceive("2");
                        callerEmitter.onCompleted();
                    }
                }).
                map(new Function<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                }).
                call(new Callee<Integer>() {
                    @Override
                    public void onCall(Release release) {
                        Log.d("kpioneer", "onCall");
                    }

                    @Override
                    public void onReceive(Integer integer) {
                        Log.d("kpioneer", "onReceive:" + integer);
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("kpioneer", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });

        Caller.
                create(new CallerOnCall<String>() {
                    @Override
                    public void call(CallerEmitter<String> callerEmitter) {
                        callerEmitter.onReceive("3");
                        callerEmitter.onReceive("4");
                        callerEmitter.onCompleted();
                    }
                }).
                lift(new CallerOperator<Integer, String>() {
                    @Override
                    public Callee<String> call(final Callee<Integer> callee) {
                        return new Callee<String>() {
                            @Override
                            public void onCall(Release release) {
                                callee.onCall(release);
                            }

                            @Override
                            public void onReceive(String s) {
                                callee.onReceive(Integer.parseInt(s));
                            }

                            @Override
                            public void onCompleted() {
                                callee.onCompleted();
                            }

                            @Override
                            public void onError(Throwable t) {
                                callee.onError(t);
                            }
                        };
                    }
                }).
                call(new Callee<Integer>() {
                    @Override
                    public void onCall(Release release) {
                        Log.d("kpioneer", "onCall");
                    }

                    @Override
                    public void onReceive(Integer integer) {
                        Log.d("kpioneer", "onReceive:" + integer);
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("kpioneer", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("kpioneer", "onError");
                    }
                });


    }
}
