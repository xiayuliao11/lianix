package com.lsl.mapper;


import com.lsl.bean.Good;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodMapper {
    /**
     * 查询所有商品
     * @return
     */
    List<Good> queryGood();

    /**
     * 根据用户id查询商品信息
     * @param id
     * @return
     */
    @Select("select * from t_good where id=#{id}")
    Good getGoodFromId(Integer id);
}
