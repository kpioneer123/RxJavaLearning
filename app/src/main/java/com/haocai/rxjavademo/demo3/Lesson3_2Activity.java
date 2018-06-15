package com.haocai.rxjavademo.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava1.Caller;
import com.haocai.mylibrary.rxJava1.NewThreadSwitcher;
import com.haocai.mylibrary.rxJava1.Receiver;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc: .RxJava1  subscribeOn仿写
 */

public class Lesson3_2Activity extends AppCompatActivity {
    public static final String TAG = "kpioneer";

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
                            stringReceiver.onReceive("test");
                            stringReceiver.onCompleted();
                        }
                    }
                }).
                callOn(new NewThreadSwitcher()).
                call(new Receiver<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onReceive(String o) {
                        Log.d(TAG, "onReceive:" + o);
                        Log.d(TAG, "currentThread:" + Thread.currentThread());
                    }
                });
    }
}
