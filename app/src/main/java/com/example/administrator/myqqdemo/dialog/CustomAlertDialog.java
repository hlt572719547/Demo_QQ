package com.example.administrator.myqqdemo.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myqqdemo.R;

public class CustomAlertDialog extends AlertDialog {

	private Context mContext;
	private String msg;
	private String leftButtonText;
	private OnClickListener leftListener;
	private String rightButtonText;
	private OnClickListener rightListener;
	private boolean cancelable;
	private String title;
	private LinearLayout contentWrap;
	private View contentView;
	private TextView content;

	/**
	 * 创建alert弹出框
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
	public CustomAlertDialog(Context context, String title, String message, String leftButtonText, String rightButtonText,
			final OnClickListener leftListener, final OnClickListener rightListener, boolean cancelable) {
		super(context, R.style.Theme_dialog);
		//super(context);
		this.mContext = context;
		this.msg = message;
		this.leftButtonText = leftButtonText;
		this.leftListener = leftListener;
		this.rightButtonText = rightButtonText;
		this.rightListener = rightListener;
		this.cancelable = cancelable;
		this.title = title;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window window = getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = window.getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		this.onWindowAttributesChanged(wl);
		
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView = inflater.inflate(R.layout.dialog_personal_login_out, null);
		content = (TextView) contentView.findViewById(R.id.content);
		LinearLayout left = (LinearLayout) contentView.findViewById(R.id.dialog_login_out);
		LinearLayout right = (LinearLayout) contentView.findViewById(R.id.dialog_cancel);
		content.setText(msg);
		if (!TextUtils.isEmpty(leftButtonText)) {
			left.setVisibility(View.VISIBLE);
			((TextView)contentView.findViewById(R.id.loginouttext)).setText(leftButtonText);
			left.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					leftListener.onClick(CustomAlertDialog.this, 0);
				}
			});
		} else {
			left.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(rightButtonText)) {
			right.setVisibility(View.VISIBLE);
			((TextView)contentView.findViewById(R.id.loginoutcanceltext)).setText(rightButtonText);
			right.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					rightListener.onClick(CustomAlertDialog.this, 1);
				}
			});
		} else {
			right.setVisibility(View.GONE);
		}
		setCancelable(cancelable);
		setCanceledOnTouchOutside(false);
		setContentView(contentView);

	}

	public void setMessage(CharSequence c) {
		content.setText(c);
	}

	public void setView(View v) {
		contentWrap.removeAllViews();
		contentWrap.addView(v);
	}
	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}
}