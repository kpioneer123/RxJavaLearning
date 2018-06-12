package com.haocai.rxjavademo.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava1.Caller;
import com.haocai.mylibrary.rxJava1.Calling;
import com.haocai.mylibrary.rxJava1.Receiver;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class Lesson1_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.testDo)
    public void onViewClicked() {

        Calling tCalling = Caller.create(new Caller.OnCall<String>() {
            @Override
            public void call(Receiver<String> stringReceiver) {
                if (!stringReceiver.isUnCalled()) {
                    stringReceiver.onReceive("test");
                    stringReceiver.onCompleted();
                }
            }
        }).call(new Receiver<String>() {
            @Override
            public void onCompleted() {
                Log.d("kpioneer", "onCompleted");
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onReceive(String s) {
                Log.d("kpioneer", "onReceive:" + s);
            }
        });

        Caller caller = Caller.create(new Caller.OnCall<String>() {
            @Override
            public void call(Receiver<String> stringReceiver) {
                if (!stringReceiver.isUnCalled()) {
                    stringReceiver.onReceive("test");
                    stringReceiver.onCompleted();
                }
            }
        });

        caller.call(new Receiver<String>() {
            @Override
            public void onCompleted() {
                Log.d("kpioneer", "onCompleted");
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onReceive(String s) {
                Log.d("kpioneer", "onReceive:" + s);
            }
        });

    }


}
