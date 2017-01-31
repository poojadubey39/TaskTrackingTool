package com.practice.tasktracking.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static  SessionFactory sessionFactory = null;

	static{
		try{
			System.out.println("Session Factory to be initialized");
			sessionFactory = new Configuration().configure().buildSessionFactory();
			System.out.println("Session Factory Initialized");
		}catch(Exception e){
			System.out.println("Error Initializing the sessionFactory");
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
