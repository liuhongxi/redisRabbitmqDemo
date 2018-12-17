package com.lhx.demo.dao;
import java.util.List;

import com.lhx.demo.entity.User;

public interface UserMapper {

	User findById(User user);

	boolean updateUser(User user);

	boolean insertUser(User user);

	boolean deleteUserById(Integer id);
	
	List< User> findUserList();
}
