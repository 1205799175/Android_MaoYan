package com.yangyuning.maoyan.mode.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/26.
 * 我的-待付款实体类
 * @author 杨宇宁
 */
public class PayBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    @NotNull
    private String title;   //影片名
    @NotNull
    private int price;   //价格

    public PayBean() {
    }

    public PayBean(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public PayBean(int id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
