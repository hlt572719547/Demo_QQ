package com.example.administrator.myqqdemo.InitView.pulltorefresh;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import me.solidev.library.R;

/**
 * Created by Vincent Woo
 * Date: 2016/6/6
 * Time: 17:37
 */
public class DefaultFooter extends BaseIndicator {
    private TextView mStringIndicator;
    private ProgressWheel progress_wheell;
    private int default_rim_color;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.lib_ptr_footer_default, parent, true);
        View child = v.getChildAt(v.getChildCount() - 1);
        mStringIndicator = (TextView) child.findViewById(R.id.tv_footer);
        progress_wheell = (ProgressWheel) v.findViewById(R.id.progress_wheell);
        default_rim_color = progress_wheell.getRimColor();
        return child;
    }

    @Override
    public void onAction() {
        mStringIndicator.setText("放开加载更多");
    }

    @Override
    public void onUnAction() {
        mStringIndicator.setText("上拉加载更多");
    }

    @Override
    public void onRestore() {
        mStringIndicator.setText("上拉加载更多");
        progress_wheell.setRimColor(default_rim_color);
        progress_wheell.stopSpinning();
    }

    @Override
    public void onLoading() {
        mStringIndicator.setText("加载中...");
        progress_wheell.setRimColor(Color.parseColor("#00000000"));
        progress_wheell.spin();
    }
}
