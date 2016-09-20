package com.example.administrator.myqqdemo.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myqqdemo.fragment.BaseFragment;
import com.example.myqqdemo.R;

/**
 * @ClassName: RecommendFragment 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-7-19 上午10:35:39
 */
public class RecommendFragment extends BaseFragment {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // initView();
    }

    @Override
    protected View onInitView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommed, null);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
