package com.practice.tasktracking.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T,K extends Serializable> {

	public int addEntity(T entity);
	
	public void deleteEntity(T entity);
	
	public List<T> findByUserId(String criteria);
	
	public List<T> findAll();
	
	public void updateEntity(T entity);
	
	public T findById(String id);

}
