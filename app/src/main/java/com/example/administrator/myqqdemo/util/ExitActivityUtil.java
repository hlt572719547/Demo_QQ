package com.example.administrator.myqqdemo.util;

import java.util.ArrayList;
import java.util.List;

import com.example.administrator.myqqdemo.AppApplication;

import android.app.Activity;
/**
 * 退出应用
 * @author lim
 *
 */
public class ExitActivityUtil {
	// 定义一个activity列表
	private List<Activity> mList = new ArrayList<Activity>();

	// 顶一一个类的实例
	private static ExitActivityUtil instance;

	// 私有构造方法 不允许创建类的实例
	private ExitActivityUtil() {
	}

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static ExitActivityUtil getInstance() {
		if (null == instance) {
			instance = new ExitActivityUtil();
		}
		return instance;
	}

	/**
	 * 如果activity已经 destory了 就移除
	 * 
	 * @param activity
	 */
	public void remove(Activity activity) {

		mList.remove(activity);

	}

	/**
	 * 添加ativity
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	/**
	 * 遍历 结束activity 并且退出
	 */
	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AppApplication.getInstance().exit();
		}
	}

}
