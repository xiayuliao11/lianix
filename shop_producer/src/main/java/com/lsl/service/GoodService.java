package com.lsl.service;


import com.lsl.bean.Good;

import java.util.List;

public interface GoodService {
    /**
     * 查询所有商品信息
     * @return
     */
    List<Good> queryGood();

    /**
     * 根据id查询所有商品信息
     * @param id
     * @return
     */
    Good getGoodFromId(Integer id);

    /**
     * 根据id查询库存数量
     * @param id
     * @return
     */
    int queryCount(Integer id);
}
