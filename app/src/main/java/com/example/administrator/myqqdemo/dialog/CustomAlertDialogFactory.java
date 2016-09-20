package com.example.administrator.myqqdemo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.text.TextUtils;
import android.view.KeyEvent;

/**
 * User: LEO Date: 12-11-17 Time: 下午8:07
 * update:2013-12-24 by Lin
 */

/**
 * 
 * =================================================================
 * 版权所有 2010-2020 佰仟金融服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；不允许
* 对程序代码以任何形式任何目的的再发布
 * =================================================================
 * @ClassName: CustomAlertDialogFactory 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-7-21 上午9:26:51
 */
public class CustomAlertDialogFactory {

	/**
	 * 创建alert弹出框,在创建带有输入框的情况下无法呼出键盘，需换用createCustomDialog方法
	 * 
	 * @param context
	 * @param title
	 *            标题，目前此字段暂未使用
	 * @param message
	 *            提示信息
	 * @param leftButtonText
	 *            左按钮文字
	 * @param rightButtonText
	 *            右按钮文字
	 * @param leftListener
	 *            左按钮点击事件
	 * @param rightListener
	 *            右按钮事件
	 * @param cancelable
	 *            是否可退出
	 * @return
	 */
	public static AlertDialog createDialog(Context context, String title, String message, String leftButtonText, String rightButtonText,
			final DialogInterface.OnClickListener leftListener, final DialogInterface.OnClickListener rightListener, boolean cancelable) {

		return new CustomAlertDialog(context, title, message, leftButtonText, rightButtonText, leftListener, rightListener, cancelable);

	}

	/**
	 * 创建弹出loading框
	 * 
	 * @param context
	 * @param message
	 *            提示信息，默认为"请稍候..."
	 * @param cancelable
	 *            是否可退出
	 * @return
	 */
	public static ProgressDialog createProgressDialog(Context context, String message, boolean cancelable) {
        final boolean cancel = cancelable;
        final Activity activity = (Activity) context;
		String msg = TextUtils.isEmpty(message) ? "请稍候..." : message;
		LoadingDialog dialog = new LoadingDialog(context, msg);
        dialog.setCancelable(cancelable);
        dialog.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    if (cancel) {
                        dialog.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
		return dialog;
	}

}
