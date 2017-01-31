package com.practice.tasktracking.service;

import java.util.Date;
import java.util.List;

import com.practice.tasktracking.dao.DAO;
import com.practice.tasktracking.dao.TaskDAO;
import com.practice.tasktracking.entities.Task;
import com.practice.tasktracking.entities.User;
import com.practice.tasktracking.enums.Status;

public class TaskService {

	private DAO<Task, String> taskDAO;
	
	public TaskService() {
		taskDAO = new TaskDAO();
	}

	public static Task getDefaultTask() {
		Task defaultTask = new Task();
		defaultTask.setTaskName("Default Task");
		return defaultTask;
	}

	public String createNewTask(String taskName, String taskContent, User user) {
		Task newTask = new Task();
		newTask.setTaskName(taskName);
		newTask.setContent(taskContent);
		newTask.getUserSet().add(user);
		int id = taskDAO.addEntity(newTask);
		return Integer.toString(id);
	}
	
	public void updateStatus(String taskId, Status status){
		Task task = new Task();
		task.setId(Integer.valueOf(taskId));
		task.setTaskStatus(status);
		taskDAO.updateEntity(task);
	}
	
	public void changeDueDate(String taskId, Date dueDate){
		Task task = new Task();
		task.setId(Integer.valueOf(taskId));
		task.setDueDate(dueDate);
		taskDAO.updateEntity(task);
	}
	
	public void addMemberToTask(String taskId, User user){
		Task task = taskDAO.findById(taskId);
		task.getUserSet().add(user);
		taskDAO.updateEntity(task);
		
	}

	public List<Task> retrieveUserTasks(int id) {
		return taskDAO.findByUserId(Integer.toString(id));
	}
	
	
	
}
