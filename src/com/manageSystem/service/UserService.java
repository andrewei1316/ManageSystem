package com.manageSystem.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.po.User;

@Transactional
public interface UserService {
	
	public void addUser(User user);
	public List<User> queryAllUser();
	public User getUserByUserLogId(String userLogId);
	public User getUserByUserId(Integer userId);
	public void updateUser(User user);
	void deleteUser(String userLogId);
}
