package com.dao;

import com.pojo.User;
import com.utils.Dao;

public interface UserDao extends Dao<User> {
	public User login(String name,String pwd);
	public int getRows();
}
