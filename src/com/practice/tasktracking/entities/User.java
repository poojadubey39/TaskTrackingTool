package com.practice.tasktracking.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_details", uniqueConstraints = { @UniqueConstraint(columnNames = "email_id") })
public class User {

	@Id
	@SequenceGenerator(name = "user-seq", sequenceName = "tasktracking_user_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user-seq")
	@Column(name = "user_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "password")
	private String password;
	
	@ManyToMany(mappedBy="userSet", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Task> taskSet = new HashSet<>();
	
	public Set<Task> getTaskSet() {
		return taskSet;
	}

	public void setTaskSet(Set<Task> taskSet) {
		this.taskSet = taskSet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		User user;
		if(obj instanceof User){
			user = (User)obj;
		}else{
			return false;
		}
		if(user.getEmailId().equals(this.getEmailId())){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", emailId=" + emailId + ", password=" + password + "]";
	}
}
