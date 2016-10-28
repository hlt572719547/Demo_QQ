package com.example.administrator.myqqdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myqqdemo.R;

/**
 * =================================================================
 * @ClassName: BaseFragment 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-7-19 上午10:33:47
 */
public abstract class BaseFragment extends Fragment {

	protected View view = null;

    // 回调不显示在屏幕上时会被调用
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) { // 消除引用
				parent.removeView(view);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(getLayoutResId(), container, false);
		onInitView(view);
		return view;
	}

	public void setHeader(int title) {
		((TextView)view.findViewById(R.id.title)).setText(getResources().getString(title));
		//((ImageView)view.findViewById(R.id.common_back)).setOnClickListener(listener);
	}

	public void setHeader(int title, View.OnClickListener listener, int colcor) {
		((TextView)view.findViewById(R.id.title)).setText(getResources().getString(title));
		((ImageView)view.findViewById(R.id.common_back)).setOnClickListener(listener);
		view.findViewById(R.id.header_layout).setBackgroundColor(getResources().getColor(colcor));
	}

	public void showToastMsg(String msg) {
		Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
	}

	protected  abstract int getLayoutResId();

	protected abstract void onInitView(View view);
}
