package com.example.administrator.myqqdemo;

import android.app.Application;
import android.app.NotificationManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * @ClassName: AppApplication 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-7-19 上午10:20:38
 */
public class AppApplication extends Application {

    public static RequestQueue queues;
    private static AppApplication mIns = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mIns = this;
        queues = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues() {
        return queues;
    }

    public static AppApplication getInstance() {
        return mIns;
    }

    /**
     * 退出应用
     */
    public void exit() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}