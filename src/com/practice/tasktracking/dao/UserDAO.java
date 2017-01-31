package com.practice.tasktracking.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.practice.tasktracking.entities.User;
import com.practice.tasktracking.hibernate.util.HibernateUtil;

public class UserDAO implements DAO<User, String> {

	private SessionFactory sessionFactory;

	public UserDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public int addEntity(User user) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		int id = (int)session.save(user);
		transaction.commit();
		session.close();
		return id;
	}

	@Override
	public void deleteEntity(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findByUserId(String parameter) {
		Session session = sessionFactory.openSession();
		String queryString = "from User where email_id LIKE :emailId";
		Query<User> query = session.createQuery(queryString);
		query.setParameter("emailId", parameter);
		List<User> userList = (List<User>)query.list();
		return userList;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public void updateEntity(User entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
		

	}

	@Override
	public User findById(String emailId) {
		Session session = sessionFactory.openSession();
		String queryString = "from User where email_id = :emailId";
		Query<User> query = session.createQuery(queryString);
		query.setParameter("emailId", emailId);
		User user =  null;
		try{
			user = query.getSingleResult();
		}catch(NoResultException e){

		}
		return user;
	}

}
