package com.lhx.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lhx.demo.entity.User;
import com.lhx.demo.service.UserService;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * 根据Id查询用户
	 */
	@GetMapping(value="/selectUserById/{id}")
	public User findUser(@PathVariable("id")  Integer id){
		return userService.findById(id);
	}
	
	/*
	 * 查询所有用户
	 */
	@GetMapping(value="/findUserList")
	public List< User> findUserList(){
		return userService.findUserList();
	}
	
	/*
	 * 更新用户信息
	 */
	@PutMapping(value="/updateUserById")
	public JSONObject updateUser(User user){
		JSONObject result =  new JSONObject();
		if (user.getId()>0) {
			if (userService.updateUser(user)) {
				result.put("info", "用户更新成功！");
			}else {
				result.put("info", "更新失败，更新接口异常！");
			}
		}else {
			result.put("info", "更新失败，用户id不能为空！");
		}
		return result;
	}
	
	/*
	 * 新增用户
	 */
	@PostMapping(value="/addUser")
	public JSONObject addUser(User user){
		JSONObject result =  new JSONObject();
		if (userService.addUser(user)) {
			result.put("info", "添加用户成功！");
		}else {
			result.put("info", "接口异常，添加用户失败！");
		}
		return result;
	}
	
	/*
	 * 删除用户（硬删除）
	 */
	@DeleteMapping(value="/deleteUserById/{id}")
	public JSONObject deleteUser(@PathVariable("id")  Integer id){
		JSONObject result =  new JSONObject();
		if (userService.deleteUserById(id)) {
			result.put("info", "删除用户成功！");
		}else {
			result.put("info", "接口异常，删除用户失败！");
		}
		return result;
	}
	
	/*
	 * 删除用户（软删除）
	 */
	@PutMapping(value="/updateStateUser/{id}")
	public JSONObject updateStateUser(@PathVariable("id")  Integer id){
		JSONObject result =  new JSONObject();
		User user = new User();
		user.setId(id);
		user.setState(-1);
		if (userService.updateUser(user)) {
			result.put("info", "软删除用户成功！");
		}else {
			result.put("info", "接口异常，软删除用户失败！");
		}
		return result;
	}
	
}

