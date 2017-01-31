package com.practice.tasktracking.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.practice.tasktracking.entities.Task;
import com.practice.tasktracking.hibernate.util.HibernateUtil;

public class TaskDAO implements DAO<Task, String> {

	private SessionFactory sessionFactory;

	public TaskDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public int addEntity(Task entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		int id = (int) session.save(entity);
		transaction.commit();
		session.close();
		return id;

	}

	@Override
	public void deleteEntity(Task entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Task> findByUserId(String criteria) {
		Session session = sessionFactory.openSession();
		String queryString = "Select t from Task t inner join t.userSet u where u.id = :userId";
		Query query = session.createQuery(queryString);
		query.setParameter("userId", Integer.valueOf(criteria));
		List<Task> taskList = (List<Task>)query.list();
		return taskList;
	}

	@Override
	public Task findById(String taskId){
		Session session = sessionFactory.openSession();
		//handle objectnotfoundexception
		Task task = (Task) session.load(Task.class, Integer.valueOf(taskId));
		return task;
	}
	@Override
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEntity(Task entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();

	}

}
