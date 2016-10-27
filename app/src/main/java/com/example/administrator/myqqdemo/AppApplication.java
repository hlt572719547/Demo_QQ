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
    public static final AppApplication INSTANCE;

    static {
        AppApplication app = null;
        try {
            app = (AppApplication) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            if (app == null)
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
        } catch (final Exception e) {
            try {
                app = (AppApplication) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (final Exception ex) {
            }
        } finally {
            INSTANCE = app;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues() {
        return queues;
    }

    public static AppApplication getInstance() {
        return INSTANCE;
    }

    /**
     * 退出应用
     */
    public void exit() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
