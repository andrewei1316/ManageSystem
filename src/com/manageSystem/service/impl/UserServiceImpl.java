package com.manageSystem.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.dao.UserDAO;
import com.manageSystem.po.User;
import com.manageSystem.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;

	@Override
	public void addUser(User user) {
		// TODO 自动生成的方法存根
		userDao.save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAllUser() {
		// TODO 自动生成的方法存根
		List<User> list = userDao.findAll();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteUser(String userLogId) {
		// TODO 自动生成的方法存根
		List<User> list = userDao.findByUserLogId(userLogId);
		if(list.size() == 0) return;
		userDao.delete(list.get(0));
	}

	@Override
	public void updateUser(User user) {
		// TODO 自动生成的方法存根
		userDao.merge(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUserLogId(String userLogId) {
		// TODO 自动生成的方法存根
		List<User> list = userDao.findByUserLogId(userLogId);
		if(list.size() == 0) return null;
		return list.get(0);
	}

	@Override
	public User getUserByUserId(Integer userId) {
		// TODO 自动生成的方法存根
		return userDao.findById(userId);
	}
	
	public void setUserDao(UserDAO userDao){
		this.userDao = userDao;
	}
	
	public UserDAO getUserDao(){
		return userDao;
	}
}
