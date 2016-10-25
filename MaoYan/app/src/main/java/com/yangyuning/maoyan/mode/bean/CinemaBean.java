package com.yangyuning.maoyan.mode.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/20.
 * 影院实体类
 * @author 姜鑫
 */
public class CinemaBean {
    private String addr;
    private String name;
    private String price;

    public CinemaBean() {
    }

    public CinemaBean(String addr, String name, String price) {
        this.addr = addr;
        this.name = name;
        this.price = price;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
