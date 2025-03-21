package com.cc.service;

import com.cc.api.UserService;
import com.cc.common.constant.ExchangeCST;
import com.cc.dao.UserDAO;
import com.cc.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/4/29 11:26
 * @Description UserServiceImpl
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<UserDO> findAllUsers() {
		return userDAO.selectAllUsers();
	}

	@Override
	public UserDO login(String username) {
		return userDAO.selectUserByName(username);
	}

	@Override
	public void addUser(UserDO userDO) {
		userDAO.insert(userDO);
	}

	@Override
	public void modifyUser(UserDO userDO) {
		userDAO.updateByPrimaryKey(userDO);
	}

	@Override
	public void deleteUser(String ids) {
		String[] userIds = ids.split(",");
		for(String userId : userIds) {
			UserDO userDO = new UserDO();
			userDO.setId(Long.valueOf(userId));
			userDO.setIsDeleted(ExchangeCST.DELETE);

			userDAO.updateByPrimaryKeySelective(userDO);
		}
	}
}
