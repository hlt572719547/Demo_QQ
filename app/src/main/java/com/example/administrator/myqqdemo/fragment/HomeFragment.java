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
 * @ClassName: HomeFragment
 * @Description: TODO +
 * @author Administrator
 * @date 2016-7-19 上午10:46:00
 */
public class HomeFragment extends BaseFragment implements OnClickListener {

    private final String     TAG             = "HomeFragment";
    private Context          ctx;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ctx = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected View onInitView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
