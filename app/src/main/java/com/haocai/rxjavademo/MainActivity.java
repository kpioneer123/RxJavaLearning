package com.haocai.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haocai.rxjavademo.demo1.Lesson1_1Activity;
import com.haocai.rxjavademo.demo1.Lesson1_2Activity;
import com.haocai.rxjavademo.demo1.Lesson1_3Activity;
import com.haocai.rxjavademo.demo1.Lesson1_4Activity;
import com.haocai.rxjavademo.demo2.Lesson2_1Activity;
import com.haocai.rxjavademo.demo2.Lesson2_2Activity;
import com.haocai.rxjavademo.demo2.Lesson2_3Activity;
import com.haocai.rxjavademo.demo2.Lesson2_4Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLesson1_1, R.id.btnLesson1_2, R.id.btnLesson1_3, R.id.btnLesson1_4, R.id.btnLesson2_1, R.id.btnLesson2_2, R.id.btnLesson2_3, R.id.btnLesson2_4, R.id.btnLesson5_1, R.id.btnLesson5_9, R.id.btnLesson5_10, R.id.btnLesson5_11, R.id.btnLesson5_15, R.id.btnLesson5_16, R.id.btnLesson5_17, R.id.btnLesson6_1, R.id.btnLesson6_4, R.id.btnLesson6_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLesson1_1:
                jump(Lesson1_1Activity.class);

                break;
            case R.id.btnLesson1_2:
                jump(Lesson1_2Activity.class);
                break;
            case R.id.btnLesson1_3:
                jump(Lesson1_3Activity.class);
                break;
            case R.id.btnLesson1_4:
                jump(Lesson1_4Activity.class);
                break;
            case R.id.btnLesson2_1:
                jump(Lesson2_1Activity.class);
                break;
            case R.id.btnLesson2_2:
                jump(Lesson2_2Activity.class);
                break;
            case R.id.btnLesson2_3:
                jump(Lesson2_3Activity.class);
                break;
            case R.id.btnLesson2_4:
                jump(Lesson2_4Activity.class);
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
