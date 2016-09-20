package com.example.administrator.myqqdemo.http;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myqqdemo.AppApplication;
import com.example.administrator.myqqdemo.util.MapUtils;

/**
 * 
 * ================================================================= 版权所有
 * 2010-2020 佰仟金融服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；不允许 对程序代码以任何形式任何目的的再发布
 * =================================================================
 * 
 * @ClassName: HttpClient
 * @Description: TODO
 * @author Administrator
 * @date 2016-7-21 上午9:23:55
 */
public class HttpClient {

	public static final int TIMEOUT_MILLONS = 5000;

	public static StringRequest stringRequest;
	public static JsonObjectRequest jsonObjectRequest;

	/**
	 * Post请求，获得返回数据
	 * 
	 * @param context
	 *            上下文
	 * @param url
	 *            发送请求的URL
	 * @param tag
	 *            TAG标签
	 * @param params
	 *            请求回调的接口（请求成功或者失败）
	 */
	public static void doJsonPost(Context context, String url, String tag,
			final JSONObject params, VolleyInterfaceJson volleyInterface) {
		Log.e("发送Get请求的URL:", url);
		// 获取全局的请求队列并把基于Tag标签的请求全部取消，防止重复请求
		AppApplication.getHttpQueues().cancelAll(tag);
		// 实例化StringRequest
		jsonObjectRequest = new JsonObjectRequest(Method.POST,
				getAbsoluteURL(url), params, volleyInterface.loadingListener(),
				volleyInterface.errorListener());
		jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
				TIMEOUT_MILLONS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		// 设置标签
		jsonObjectRequest.setTag(tag);
		// 将请求添加至队列里面
		AppApplication.getHttpQueues().add(jsonObjectRequest);
		// 启动
		AppApplication.getHttpQueues().start();
	}

	/**
	 * Get请求，获得返回数据
	 * 
	 * @param context
	 *            上下文
	 * @param url
	 *            发送请求的URL
	 * @param tag
	 *            TAG标签
	 * @param params
	 *            请求回调的接口（请求成功或者失败）
	 */
	public static void doGet(Context context, String url, String tag,
			final Map<String, String> params, VolleyInterface volleyInterface) {
		Log.e("发送Get请求的URL:", url);
		// 获取全局的请求队列并把基于Tag标签的请求全部取消，防止重复请求
		AppApplication.getHttpQueues().cancelAll(tag);
		String URL = null;
		if (params != null) {
			URL = getUrlWithQueryString(getAbsoluteURL(url),
					getAESParams(params));
			// URL = getUrlWithQueryString(getAbsoluteURL(url), params);
		} else {
			URL = getAbsoluteURL(url);
		}
		// 实例化StringRequest
		stringRequest = new StringRequest(Method.GET, URL,
				volleyInterface.loadingListener(),
				volleyInterface.errorListener());
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MILLONS,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		// 设置标签
		stringRequest.setTag(tag);
		// 将请求添加至队列里面
		AppApplication.getHttpQueues().add(stringRequest);
		// 启动
		AppApplication.getHttpQueues().start();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param context
	 *            上下文
	 * @param url
	 *            发送请求的URL
	 * @param tag
	 *            TAG标签
	 * @param params
	 *            请求参数，请求参数应该是Hashmap类型
	 * @param params
	 *            请求回调的接口（请求成功或者失败）
	 */
	public static void doPost(Context context, String url, String tag,
			final Map<String, String> params, VolleyInterface volleyInterface) {
		Log.e("发送Post请求的URL:", url);
		// 获取全局的请求队列并把基于Tag标签的请求全部取消，防止重复请求
		AppApplication.getHttpQueues().cancelAll(tag);
		stringRequest = new StringRequest(Method.POST, getAbsoluteURL(url),
				volleyInterface.loadingListener(),
				volleyInterface.errorListener()) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return getAESParams(params);
				// return params;
			}
		};
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MILLONS,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		// 设置标签
		stringRequest.setTag(tag);
		// 加入队列
		AppApplication.getHttpQueues().add(stringRequest);
		// 启动
		AppApplication.getHttpQueues().start();
	}

	public static String getAbsoluteURL(String url) {
		if (ConstantUrl.isIpUrl) {
			return ConstantUrl.BASE_URL_IP + url;
		} else {
            return ConstantUrl.BASE_URL + url;
        }
	}

	public static String getUrlWithQueryString(String url,
			Map<String, String> params) {
		if (params != null) {
			String paramString = MapUtils.mapToString(params);
			if (url.indexOf("?") == -1) {
				url += "?" + paramString;
			} else {
				url += "&" + paramString;
			}
		}
		return url;
	}

	//MAP参数转为get传参方式.sdk6.0不支持了
	/*public static String getParamString(Map<String, String> params) {
		List<BasicNameValuePair> lparams = new LinkedList<BasicNameValuePair>();

		for (ConcurrentHashMap.Entry<String, String> entry : params.entrySet()) {
			lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return URLEncodedUtils.format(lparams, "UTF-8");
	}*/

	public static Map<String, String> getAESParams(Map<String, String> params) {
		Map<String, String> paramsAES = new HashMap<String, String>();
		for (String key : params.keySet()) {
			paramsAES.put(key,
					CryptAES.AES_Encrypt(CryptAES.KEY, params.get(key)));
		}

		return paramsAES;
	}
}
