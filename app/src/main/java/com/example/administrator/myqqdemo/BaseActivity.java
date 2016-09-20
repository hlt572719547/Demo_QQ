package com.example.administrator.myqqdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myqqdemo.R;

/**
 * @ClassName: BaseActivity 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-9-21 上午9:20:16
 */
public abstract class BaseActivity extends FragmentActivity {

    public ProgressDialog mWaitDialeog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setHeader(String title) {
        ((TextView) findViewById(R.id.title)).setText(title);
        //((ImageView) findViewById(R.id.common_back)).setOnClickListener(listener);
    }

    public void setHeader(int title, OnClickListener listener, int colcor) {
        ((TextView) findViewById(R.id.title)).setText(getResources().getString(title));
        ((ImageView) findViewById(R.id.common_back)).setOnClickListener(listener);
        findViewById(R.id.header_layout).setBackgroundColor(getResources().getColor(colcor));
    }

    public void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
