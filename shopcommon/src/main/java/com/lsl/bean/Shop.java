package com.lsl.bean;


import java.io.Serializable;

public class Shop implements Serializable {

    Integer id;
    Integer goodCount;
    Integer userId;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", goodCount=" + goodCount +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
