package com.cc.dao;

import com.cc.model.ProductAndOrderDO;

public interface ProductAndOrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ProductAndOrderDO record);

    int insertSelective(ProductAndOrderDO record);

    ProductAndOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAndOrderDO record);

    int updateByPrimaryKey(ProductAndOrderDO record);
}