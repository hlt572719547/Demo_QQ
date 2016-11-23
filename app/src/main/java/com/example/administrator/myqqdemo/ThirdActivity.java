package com.example.administrator.myqqdemo;

import android.os.Bundle;

import com.example.administrator.myqqdemo.R;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setHeader("第三个");

        findViewById(R.id.content).setBackgroundColor(getResources().getColor(R.color.yellow));

    }
}
