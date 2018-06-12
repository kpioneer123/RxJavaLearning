package com.haocai.rxjavademo.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava1.Caller;
import com.haocai.mylibrary.rxJava1.Func1;
import com.haocai.mylibrary.rxJava1.Receiver;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc: 仿写RxJava 操作符方法
 */

public class Lesson2_2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.testDo)
    public void onViewClicked() {
        Caller.
                create(new Caller.OnCall<String>() {
                    @Override
                    public void call(Receiver<String> stringReceiver) {
                        if (!stringReceiver.isUnCalled()) {
                            stringReceiver.onReceive("1");
                            stringReceiver.onReceive("2");
                            stringReceiver.onCompleted();
                        }
                    }
                }).
                map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s)+2;
                    }
                }).
                call(new Receiver<Integer>() {
                    @Override
                    public void onCompleted() {

                        Log.d("kpioneer", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onReceive(Integer integer) {
                        Log.d("kpioneer", "onReceive:" + integer);
                    }
                });
    }
}
