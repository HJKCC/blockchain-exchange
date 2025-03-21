package com.cc.dao;

import com.cc.model.ProductOrderDO;

public interface ProductOrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ProductOrderDO record);

    int insertSelective(ProductOrderDO record);

    ProductOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductOrderDO record);

    int updateByPrimaryKey(ProductOrderDO record);
}