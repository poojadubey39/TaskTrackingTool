package com.practice.tasktracking.service;

import java.util.List;

import com.practice.tasktracking.dao.DAO;
import com.practice.tasktracking.dao.UserDAO;
import com.practice.tasktracking.entities.Task;
import com.practice.tasktracking.entities.User;

public class UserService {

	private DAO<User, String> userDAO;

	public UserService() {
		userDAO = new UserDAO();
	}

	public int registerUser(String emailId, String password, String name) {
		User user = userDAO.findById(emailId);
		if (user == null) {
			User newUser = new User();
			newUser.setEmailId(emailId);
			newUser.setPassword(password);
			newUser.setName(name);
			Task defaultTask = TaskService.getDefaultTask();
			defaultTask.getUserSet().add(newUser);
			newUser.getTaskSet().add(defaultTask);
			return userDAO.addEntity(newUser);
		} else {
			return -1;
		}
	}

	public User loginUser(String emailId, String password) {
		User user = userDAO.findById(emailId);
		if(user != null){
			if(user.getPassword().equals(password)){
				return user;
			}else{
				return null;
			}
		}else{
			return user;
		}
		
	}

	public List<User> searchUsers(String searchParameter) {
		List<User> userList = userDAO.findByUserId(searchParameter);
		return userList;
	}
	
	public User findById(int id){
		return userDAO.findById(Integer.toString(id));
	}
}
