package com.cc.dao;

import com.cc.model.UserDO;

import java.util.List;

public interface UserDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    List<UserDO> selectAllUsers();

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    UserDO selectUserByName(String username);
}