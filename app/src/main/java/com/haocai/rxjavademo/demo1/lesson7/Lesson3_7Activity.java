package com.haocai.rxjavademo.demo1.lesson7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava2.backpressure.Drop;
import com.haocai.mylibrary.rxJava2.backpressure.Receiver;
import com.haocai.mylibrary.rxJava2.backpressure.Telephoner;
import com.haocai.mylibrary.rxJava2.backpressure.TelephonerEmitter;
import com.haocai.mylibrary.rxJava2.backpressure.TelephonerOnCall;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class Lesson3_7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_7);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.async)
    public void onViewClicked() {
        Telephoner.create(new TelephonerOnCall<String>() {
            @Override
            public void call(TelephonerEmitter<String> telephonerEmitter) {
                telephonerEmitter.onReceive("test");
                telephonerEmitter.onCompleted();
            }
        }).call(new Receiver<String>() {
            @Override
            public void onCall(Drop d) {
                d.request(Long.MAX_VALUE); //注释带调 该句 话 就不会打印onReceive:test
                Log.d("kpioneer", "onCall");
            }

            @Override
            public void onReceive(String s) {
                Log.d("kpioneer", "onReceive:" + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

                Log.d("kpioneer", "onCompleted");
            }
        });
    }
}
