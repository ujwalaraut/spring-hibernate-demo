package com.wcs.crud.webapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcs.crud.webapp.dao.UserDao;
import com.wcs.crud.webapp.model.User;
import com.wcs.crud.webapp.service.UserService;

@Service
public class UserServiceImpl implements UserService

{
    @Autowired
	private UserDao dao;

    @Override
	public int saveUser(User user)
	{
		return dao.saveUser(user);
	}
    
	@Override
	public int loginCheck(User user) {

		return dao.loginCheck(user);
	}

	@Override
	public List<User> displayAll(User user) {

		return dao.dispalyAll(user);
	}

	@Override
	public int deleteUser(User user) {

		return dao.deleteUser(user);
	}

	@Override
	public User editUser(User user) {

		return dao.editUser(user);
	}

	@Override
	public int updateUser(User user) {

		return dao.updateUser(user);
	}

}
