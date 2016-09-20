package com.example.administrator.myqqdemo.http;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;

/**
 * 
 * =================================================================
 * 版权所有 2010-2020 佰仟金融服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；不允许
* 对程序代码以任何形式任何目的的再发布
 * =================================================================
 * @ClassName: VolleyInterface 
 * @Description: 请求成功或失败的接口回调
 * @author Administrator
 * @date 2016-7-21 上午9:24:09
 */
public abstract class VolleyInterface {
    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 请求成功监听
     */
    public static Listener<String> mListener;
    /**
     * 请求失败监听
     */
    public static ErrorListener mErrorListtener;

    public VolleyInterface(Context context, Listener<String> listener, ErrorListener errorListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mErrorListtener = errorListener;
    }

    /**
     * 请求成功的抽象类
     *
     * @param result
     */
    public abstract void onSuccess(String result);

    /**
     * 请求失败的抽象类
     *
     * @param error
     */
    public abstract void onError(VolleyError error);

    /**
     * 请求成功监听
     *
     * @return
     */
    public Listener<String> loadingListener() {
        mListener = new Listener<String>() {

            @Override
            public void onResponse(String result) {
            	String decResult = CryptAES.AES_Decrypt(CryptAES.KEY, result);
                onSuccess(decResult);
            }
        };
        return mListener;
    }

    /**
     * 请求失败监听
     *
     * @return
     */
    public ErrorListener errorListener() {
        mErrorListtener = new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求失败返回的数据：", error.toString());
                onError(error);
            }
        };
        return mErrorListtener;
    }
}
