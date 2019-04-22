package com.lsl.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="good")
public class NewGoods {
    Integer userid;
    List<ShopGood> list;

    @Override
    public String toString() {
        return "NewGoods{" +
                "userid='" + userid + '\'' +
                ", list=" + list +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public List<ShopGood> getList() {
        return list;
    }

    public void setList(List<ShopGood> list) {
        this.list = list;
    }
}
