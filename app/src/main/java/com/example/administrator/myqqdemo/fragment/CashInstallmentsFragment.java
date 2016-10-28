package com.example.administrator.myqqdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.administrator.myqqdemo.InitView.StatusViewLayout;
import com.example.administrator.myqqdemo.InitView.pulltorefresh.PullToRefresh;
import com.example.administrator.myqqdemo.adapter.MultiTypeAdapter;
import com.example.administrator.myqqdemo.adapter.wrapper.HeaderAndFooterWrapper;
import com.example.administrator.myqqdemo.adapter.wrapper.LoadMoreWrapper;
import com.example.administrator.myqqdemo.fragment.BaseFragment;
import com.example.administrator.myqqdemo.model.Item;
import com.example.myqqdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CashInstallmentsFragment 
 * @Description: TODO 
 * @author Administrator
 * @date 2016-7-19 上午10:33:39
 */
public class CashInstallmentsFragment<E extends Item> extends BaseFragment implements OnClickListener {
    private RecyclerView mRecycleView;
    private StatusViewLayout mStatusViewLayout;
    private PullToRefresh mPullToRefresh;

    private List<E> mItems;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_cash_installments;
    }

    @Override
    protected void onInitView(View view) {
        mRecycleView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mItems = new ArrayList<>();
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(new MultiTypeAdapter(mItems));
        mAdapter = new LoadMoreWrapper(getContext(), mHeaderAndFooterWrapper);
        mAdapter.setOnLoadListener(new LoadMoreWrapper.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleView.setAdapter(mAdapter);

        mPullToRefresh = (PullToRefresh) view.findViewById(R.id.pull_to_refresh);
        mPullToRefresh.setPullUpEnable(false);
        mPullToRefresh.setListener(new PullToRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
            }
        });

        mStatusViewLayout = (StatusViewLayout) view.findViewById(R.id.status_bar_latest_event_content);
        mStatusViewLayout.setOnRetryListener(new View.OnClickListener() {//错误重试
            @Override
            public void onClick(View v) {
                mStatusViewLayout.showLoading();

            }
        });
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
