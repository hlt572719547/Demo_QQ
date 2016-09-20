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
 * 
 * =================================================================
 * 版权所有 2010-2020 佰仟金融服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；不允许
* 对程序代码以任何形式任何目的的再发布
 * =================================================================
 * @ClassName: BaseFragment 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-7-19 上午10:33:47
 */
public abstract class BaseFragment extends Fragment {
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
		if (view != null) {
			return view;
		}
		view = onInitView(inflater, container, savedInstanceState);
		return view;
	}

	protected abstract View onInitView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

	protected View view = null;

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
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
	}
}
