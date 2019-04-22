package com.lsl.service.impl;


import com.lsl.bean.Good;
import com.lsl.mapper.GoodMapper;
import com.lsl.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodMapper mapper;


    @Override
    public List<Good> queryGood() {
        List<Good> goods = mapper.queryGood();
        return goods;
    }

    @Override
    public Good getGoodFromId(Integer id) {
        return mapper.getGoodFromId(id);
    }
}
