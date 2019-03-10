package com.utils;

import java.util.List;

public interface Dao<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(Integer id);
	public T findById(Integer id);
	public List<T> findAll();
	
}
