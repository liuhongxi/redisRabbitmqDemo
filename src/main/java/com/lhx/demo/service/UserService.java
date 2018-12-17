package com.lhx.demo.service;

import java.util.List;

import com.lhx.demo.entity.User;

public interface UserService {
	
	User findById(Integer id);
	
	boolean deleteUserById(Integer id);
	
	boolean updateUser(User user);
 
	boolean addUser(User user);
	
	List< User> findUserList();
}
