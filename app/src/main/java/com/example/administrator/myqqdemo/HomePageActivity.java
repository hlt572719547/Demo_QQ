package com.example.administrator.myqqdemo;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.administrator.myqqdemo.adapter.HomePagerAdapter;
import com.example.administrator.myqqdemo.fragment.CashInstallmentsFragment;
import com.example.administrator.myqqdemo.fragment.HomeFragment;
import com.example.administrator.myqqdemo.fragment.RecommendFragment;
import com.example.administrator.myqqdemo.util.ActivityUtil;
import com.example.administrator.myqqdemo.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: HomePageActivity
 * @Description: TODO
 * @author Administrator
 * @date 2016-9-19 上午10:20:10
 */
public class HomePageActivity extends SlidingFragmentActivity {

    private LinearLayout bottomBar;
    private ViewPager viewPager;
    private int[] slideMenuLables = new int[] { R.string.home_label_message, R.string.home_label_contacts, R.string.home_label_status };
    private int[] slideMenuDrawbles  = new int[] { R.drawable.homepage_tabbar_mine, R.drawable.homepage_tabbar_repay,
            R.drawable.homepage_tabbar_discover };
    private Class[] activitis  = new Class[] {FirstActivity.class, SecondActivity.class, ThirdActivity.class};

    private int[] lables = new int[] { R.string.home_label_message, R.string.home_label_contacts, R.string.home_label_status };
    private int[] images  = new int[] { R.drawable.homepage_tabbar_mine, R.drawable.homepage_tabbar_repay,
            R.drawable.homepage_tabbar_discover };
    private Class[] fragmentArray  = new Class[] {HomeFragment.class, CashInstallmentsFragment.class, RecommendFragment.class};
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private HomePagerAdapter adapter = null;
    private int currentTabIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_page);

        initView();
        initPages();
        initSlideMenu();
    }

    private void initView() {
        bottomBar = (LinearLayout) findViewById(R.id.navibar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // 计算底部导航条单个item宽度
        int itemWidth = getWindowManager().getDefaultDisplay().getWidth()/ lables.length;
        // bottombar布局三个内容
        for (int i = 0; i < lables.length; i++) {
            // 每一个按键是不是由 文字与图标
            View item = View.inflate(this, R.layout.tab_item_view, null);
            ImageView image = (ImageView) item.findViewById(R.id.imageview);
            TextView text = (TextView) item.findViewById(R.id.textview);

            item.setId(i);

            image.setImageResource(images[i]);
            text.setText(getResources().getString(lables[i]));
            bottomBar.addView(item, itemWidth, LinearLayout.LayoutParams.MATCH_PARENT);

            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentTabIndex = v.getId();
                    viewPager.setCurrentItem(v.getId());
                    ((TextView)findViewById(R.id.title)).setText(getResources().getString(lables[v.getId()]));
                }
            });
            ((TextView)findViewById(R.id.title)).setText(getResources().getString(lables[currentTabIndex]));
            findViewById(R.id.common_back).setVisibility(View.VISIBLE);
            ((ImageView)findViewById(R.id.common_back)).setImageDrawable(getResources().getDrawable(R.drawable.common_header_right));
            findViewById(R.id.common_back).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getSlidingMenu().isMenuShowing()) {
                        getSlidingMenu().showContent();
                    } else {
                        getSlidingMenu().showMenu();
                    }
                }
            });
            try {
                fragments.add((Fragment)fragmentArray[i].newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void initPages() {
        adapter = new HomePagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    private void initSlideMenu() {
        // add a dummy view
        View v = new View(this);
        setBehindContentView(v);
        getSlidingMenu().setSlidingEnabled(true);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeEnabled(true);
        sm.setBehindScrollScale(0.25f);
        sm.setFadeDegree(0.25f);

        sm.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, -canvas.getWidth() / 2,
                        canvas.getHeight() / 2);
            }
        });

        //绘制侧滑菜单内容
        LinearLayout menuLayout = new LinearLayout(this);
        menuLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        menuLayout.setOrientation(LinearLayout.VERTICAL);
        menuLayout.setGravity(Gravity.CENTER_VERTICAL);
        menuLayout.setBackground(getResources().getDrawable(R.drawable.background_pic));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,0,0,12);
        for (int i = 0; i<slideMenuLables.length; i++) {
            menuLayout.addView(getTabItemView(i), lp);
        }
        sm.setMenu(menuLayout);
    }

    private View getTabItemView(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab_item, null);
        view.setTag(i);
        ImageView mImageView = (ImageView) view.findViewById(R.id.tab_icon);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_title);
        mImageView.setBackgroundResource(slideMenuDrawbles[i]);
        mTextView.setText(slideMenuLables[i]);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.next(HomePageActivity.this,activitis[(int)v.getTag()] );
            }
        });
        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * @Title: exit
     * @return void
     */
    private void exit() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }

}
