package com.haocai.rxjavademo.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava2.Callee;
import com.haocai.mylibrary.rxJava2.Caller;
import com.haocai.mylibrary.rxJava2.CallerEmitter;
import com.haocai.mylibrary.rxJava2.CallerOnCall;
import com.haocai.mylibrary.rxJava2.Release;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class Lesson1_3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.testDo)
    public void onViewClicked() {
        Caller.create(new CallerOnCall<String>() {
            @Override
            public void call(CallerEmitter<String> callerEmitter) {
                callerEmitter.onReceive("test");
                callerEmitter.onCompleted();
            }
        }).call(new Callee<String>() {
            @Override
            public void onCall(Release release) {
                Log.d("kpioneer", "onCall");
            }

            @Override
            public void onReceive(String s) {
                Log.d("kpioneer", "onReceive:" + s);
            }

            @Override
            public void onCompleted() {
                Log.d("kpioneer", "onCompleted");
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
