package com.cc.dao;

import com.cc.model.PayOrderDO;

public interface PayOrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(PayOrderDO record);

    int insertSelective(PayOrderDO record);

    PayOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayOrderDO record);

    int updateByPrimaryKey(PayOrderDO record);
}