package com.example.administrator.myqqdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.administrator.myqqdemo.fragment.BaseFragment;
import com.example.myqqdemo.R;

/**
 * 
 * =================================================================
 * 版权所有 2010-2020 佰仟金融服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；不允许
* 对程序代码以任何形式任何目的的再发布
 * =================================================================
 * @ClassName: CashInstallmentsFragment 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-7-19 上午10:33:39
 */
public class CashInstallmentsFragment extends BaseFragment implements OnClickListener {
    private Context mContext;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = this.getActivity();
    }

    @Override
    protected View onInitView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash_installments, null);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {

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
    }
}
