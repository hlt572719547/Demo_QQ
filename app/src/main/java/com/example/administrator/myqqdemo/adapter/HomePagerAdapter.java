package com.example.administrator.myqqdemo.adapter;

import java.util.ArrayList;
import java.util.List;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @ClassName: HomePagerAdapter
 * @Description: TODO 
 * @author Administrator
 * @date 2016-9-19 上午10:28:04
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<Fragment>();

    public HomePagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragments = list;
    }
    //返回指定下标的Fragment 显示在屏幕上 
    @Override
    public Fragment getItem(int index) {
        return fragments.get(index);
    }

    //返回页面数据
    @Override
    public int getCount() {
        return fragments.size();
    }

}
