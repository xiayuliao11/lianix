package com.lsl.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Good{
    Integer id;
    String goodName;
    Integer goodCount;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date   goodTime;
    double goodPrice;
    String  goodImg;
    String  goodNorms;
    String  goodVender;
    Integer userId;
    double  hj;

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                ", goodCount=" + goodCount +
                ", goodTime=" + goodTime +
                ", goodPrice=" + goodPrice +
                ", goodImg='" + goodImg + '\'' +
                ", goodNorms='" + goodNorms + '\'' +
                ", goodVender='" + goodVender + '\'' +
                ", userId=" + userId +
                ", hj=" + hj +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Date getGoodTime() {
        return goodTime;
    }

    public void setGoodTime(Date goodTime) {
        this.goodTime = goodTime;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public String getGoodNorms() {
        return goodNorms;
    }

    public void setGoodNorms(String goodNorms) {
        this.goodNorms = goodNorms;
    }

    public String getGoodVender() {
        return goodVender;
    }

    public void setGoodVender(String goodVender) {
        this.goodVender = goodVender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getHj() {
        return hj;
    }

    public void setHj(double hj) {
        this.hj = hj;
    }
}

