package com.haocai.rxjavademo.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haocai.mylibrary.rxJava2.Callee;
import com.haocai.mylibrary.rxJava2.Caller;
import com.haocai.mylibrary.rxJava2.CallerEmitter;
import com.haocai.mylibrary.rxJava2.CallerOnCall;
import com.haocai.mylibrary.rxJava2.NewThreadSwitcher;
import com.haocai.mylibrary.rxJava2.Release;
import com.haocai.mylibrary.rxJava2.backpressure.Drop;
import com.haocai.mylibrary.rxJava2.backpressure.Receiver;
import com.haocai.mylibrary.rxJava2.backpressure.Telephoner;
import com.haocai.mylibrary.rxJava2.backpressure.TelephonerEmitter;
import com.haocai.mylibrary.rxJava2.backpressure.TelephonerOnCall;
import com.haocai.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Xionghu on 2018/6/11.
 * Desc: .RxJava2  subscribeOn仿写
 */

public class Lesson3_3Activity extends AppCompatActivity {
    public static final String TAG = "kpioneer";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.testDo)
    public void onViewClicked() {
         /*---------无背压---------*/
        Caller.
                create(new CallerOnCall<String>() {
                    @Override
                    public void call(CallerEmitter<String> callerEmitter) {
                        callerEmitter.onReceive("test");
                        callerEmitter.onCompleted();
                    }
                }).
                callOn(new NewThreadSwitcher()).
                call(new Callee<String>() {
                    @Override
                    public void onCall(Release release) {

                    }

                    @Override
                    public void onReceive(String string) {
                        Log.d(TAG, "无背压：onReceive:" + string);
                        Log.d(TAG, "无背压：currentThread:" + Thread.currentThread());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });

        /*---------有背压---------*/
        Telephoner.
                create(new TelephonerOnCall<String>() {
                    @Override
                    public void call(TelephonerEmitter<String> telephonerEmitter) {
                        telephonerEmitter.onReceive("test");
                        telephonerEmitter.onCompleted();
                    }
                }).
                callOn(new NewThreadSwitcher()).
                call(new Receiver<String>() {
                    @Override
                    public void onCall(Drop d) {
                        d.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onReceive(String s) {
                        Log.d(TAG, "有背压：onReceive:" + s);
                        Log.d(TAG, "有背压：currentThread:" + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }
}
