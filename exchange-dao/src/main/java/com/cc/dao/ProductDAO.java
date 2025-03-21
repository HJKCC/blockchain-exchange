package com.cc.dao;

import com.cc.model.ProductDO;

import java.util.List;

public interface ProductDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDO record);

    int insertSelective(ProductDO record);

    ProductDO selectByPrimaryKey(Long id);

    List<ProductDO> selectAllProducts();

    int updateByPrimaryKeySelective(ProductDO record);

    int updateByPrimaryKey(ProductDO record);
}