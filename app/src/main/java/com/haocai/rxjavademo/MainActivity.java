package com.haocai.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haocai.rxjavademo.demo1.lesson1.Lesson3_1Activity;
import com.haocai.rxjavademo.demo1.lesson5.Lesson3_5Activity;
import com.haocai.rxjavademo.demo1.lesson6.Lesson3_6Activity;
import com.haocai.rxjavademo.demo1.lesson7.Lesson3_7Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLesson3_1, R.id.btnLesson3_5, R.id.btnLesson3_6, R.id.btnLesson3_7, R.id.btnLesson4_1, R.id.btnLesson4_4, R.id.btnLesson4_5, R.id.btnLesson4_6, R.id.btnLesson5_1, R.id.btnLesson5_9, R.id.btnLesson5_10, R.id.btnLesson5_11, R.id.btnLesson5_15, R.id.btnLesson5_16, R.id.btnLesson5_17, R.id.btnLesson6_1, R.id.btnLesson6_4, R.id.btnLesson6_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLesson3_1:
                jump(Lesson3_1Activity.class);

                break;
            case R.id.btnLesson3_5:
                jump(Lesson3_5Activity.class);
                break;
            case R.id.btnLesson3_6:
                jump(Lesson3_6Activity.class);
                break;
            case R.id.btnLesson3_7:
                jump(Lesson3_7Activity.class);
                break;
            case R.id.btnLesson4_1:
                break;
            case R.id.btnLesson4_4:
                break;
            case R.id.btnLesson4_5:
                break;
            case R.id.btnLesson4_6:
                break;
            case R.id.btnLesson5_1:
                break;
            case R.id.btnLesson5_9:
                break;
            case R.id.btnLesson5_10:
                break;
            case R.id.btnLesson5_11:
                break;
            case R.id.btnLesson5_15:
                break;
            case R.id.btnLesson5_16:
                break;
            case R.id.btnLesson5_17:
                break;
            case R.id.btnLesson6_1:
                break;
            case R.id.btnLesson6_4:
                break;
            case R.id.btnLesson6_5:
                break;
        }
    }
    private void jump(Class<?> activity) {
        Intent tIntent = new Intent();
        tIntent.setClass(this, activity);
        startActivity(tIntent);
    }
}
