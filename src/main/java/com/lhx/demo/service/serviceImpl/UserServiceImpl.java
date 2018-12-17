package com.lhx.demo.service.serviceImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.classmate.util.ResolvedTypeCache.Key;
import com.lhx.demo.dao.UserMapper;
import com.lhx.demo.entity.User;
import com.lhx.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private RedisTemplate redisTemplate;
	

	@Override
	public User findById(Integer id) {
		//从缓存中那用户信息
		String key = "user_" +id;
		ValueOperations<String , User> operations  = redisTemplate.opsForValue();
		//缓存存在
		boolean haskey =  redisTemplate.hasKey(key);
		if (haskey) {
			User user = operations.get(key);
			LOGGER.info("findById从redis缓存中获取了 "+user.getUsername()+" 的用户信息");
			return user;
		}
		//从数据库获取用户信息并写到redis中
		User user = new User();
		user.setId(id);
		user = userDao.findById(user);
		System.out.println("key:  "+key);
		operations.set(key,user);
		LOGGER.info("findById从数据库读取了 "+ user.getUsername()+" 用户信息并插入redis缓存 ");
		return user;
	}

	@Override
	public List<User> findUserList() {	
		return userDao.findUserList();
	}
	
	@Override
	public boolean deleteUserById(Integer id) {
		boolean bo =  true;
		try {
			bo = userDao.deleteUserById(id);
		} catch (Exception e) {
			bo = false;
			LOGGER.info("删除接口异常！");
		}
		return bo;
	}

	@Override
	public boolean updateUser(User user) {
		boolean bo =  true;
		String key = "user_" +user.getId();
		try {
			//缓存存在
			boolean haskey =  redisTemplate.hasKey(key);
			System.out.println(user.getId() + " 在rdis缓存是否存在："+ haskey);
			if (haskey) {
				redisTemplate.delete(key );
				System.out.println("rdis缓存清除");
			}
			bo = userDao.updateUser(user);
		} catch (Exception e) {
			bo = false;
			LOGGER.info("更新接口异常！");
		}
		return bo;
	}

	@Override
	public boolean addUser(User user) {
		boolean bo =  true;
		try {
			bo = userDao.insertUser(user);
		} catch (Exception e) {
			bo = false;
			LOGGER.info("新增接口异常！");
		}
		return bo;
	}


}
