package com.example.administrator.myqqdemo.model;

import android.support.annotation.NonNull;

import com.example.administrator.myqqdemo.adapter.ItemViewProvider;
import com.example.administrator.myqqdemo.adapter.ViewHolder;
import com.example.myqqdemo.R;


/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:11
 */

public class TextItemViewProvider extends ItemViewProvider<TestItem> {


    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_news_text;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TestItem item) {
        holder.setText(R.id.tv_title, item.getTitile());
        holder.setText(R.id.tv_content, item.getContent());
        holder.setText(R.id.tv_date, item.getData());
    }

}
