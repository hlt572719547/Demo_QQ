package com.example.administrator.myqqdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.administrator.myqqdemo.dialog.CustomAlertDialogFactory;
import com.example.myqqdemo.R;


/**
 * @author Leo
 * @Description: 活动web页面
 */
public class ActivityWebViewActivity extends BaseActivity implements
		View.OnClickListener {
	private String URL;
	private String title;
	private WebView mWebView;
	private Activity mContext;
	private AlertDialog mAlert;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_web_view);

		Intent intent = getIntent();
		if (intent == null) {
			URL = "https://www.baidu.com";
			title = "百度";
		} else {
			URL = intent.getStringExtra("URL_KEY");
			title = intent.getStringExtra("URL_TITLE");
		}

		setHeader(title);
		initView();
		initWebView();
		mWebView.loadUrl(URL);
//		mWebView.setDownloadListener(new DownloadListener() {
//			@Override
//			public void onDownloadStart(String s, String s2, String s3,
//					String s4, long l) {
//				// 启动系统浏览器开始下载
//				Intent intent = new Intent();
//				intent.setAction("android.intent.action.VIEW");
//				Uri content_url = Uri.parse(s);
//				intent.setData(content_url);
//				startActivity(intent);
//			}
//		});
	}

	private void initView() {
		
		TextView tvTitle = (TextView) findViewById(R.id.title);
		tvTitle.setText(title);
		findViewById(R.id.common_back).setOnClickListener(this);
		mWebView = (WebView) findViewById(R.id.webView);

		mWaitDialeog = CustomAlertDialogFactory.createProgressDialog(this,
				"请稍后。。。", true);
		mWaitDialeog
				.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						mWebView.stopLoading();
					}
				});

	}
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.setInitialScale(100);
		mWebView.getSettings().setSupportZoom(true);
		mWebView.getSettings().setBuiltInZoomControls(false);

		// 覆盖加载url方法
		mWebView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				view.stopLoading();
				view.clearView();
				showToastMsg(description);
			}
		});
		// 设置加载页面样式
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (mWebView == null || !mWebView.isShown())// 修正用户在载入网页的过程中退出导致系统崩溃
					return;

				if (newProgress == 100) {
					if (mWaitDialeog.isShowing()) {
						mWaitDialeog.dismiss();
					}
				} else {
					if (!mWaitDialeog.isShowing()) {
						mWaitDialeog.show();
					}
				}
				super.onProgressChanged(view, newProgress);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.common_back:
			goBack();
			break;
		default:
			break;
		}
	}

	/**
	 * 处理返回
	 */
	private void goBack() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mWebView.setWebChromeClient(null);
	}
}