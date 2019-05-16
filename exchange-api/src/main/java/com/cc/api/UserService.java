package com.cc.api;

import com.cc.model.UserDO;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/4/29 10:39
 * @Description UserService
 */
public interface UserService {
	List<UserDO> findAllUsers();

	UserDO login(String username);

	void addUser(UserDO userDO);
}
