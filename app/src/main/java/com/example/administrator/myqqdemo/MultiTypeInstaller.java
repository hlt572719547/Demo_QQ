package com.example.administrator.myqqdemo;

import com.example.administrator.myqqdemo.adapter.MultiTypePool;
import com.example.administrator.myqqdemo.model.TestItem;
import com.example.administrator.myqqdemo.model.TextNewsItemViewProvider;

/**
 * Created by _SOLID
 * Date:2016/10/9
 * Time:10:53
 * Desc:
 */

public class MultiTypeInstaller {
    public static void install() {

        MultiTypePool.register(TestItem.class, new TextNewsItemViewProvider());
    }
}
