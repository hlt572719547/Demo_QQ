package com.example.administrator.myqqdemo.model;

/**
 * Created by Administrator on 2016/10/28.
 */

public class TestItem implements Item {

    private String titile;
    private String content;
    private String data;

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
