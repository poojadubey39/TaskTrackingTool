package com.practice.tasktracking.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.practice.tasktracking.enums.Status;

@Entity
@Table(name = "task_details")
@DynamicUpdate(value=true)
public class Task {

	@Id
	@SequenceGenerator(name = "task-seq", sequenceName = "user_task_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task-seq")
	@Column(name = "task_id")
	private int id;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "content")
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(name = "task_status")
	private Status taskStatus;

	@Column(name = "due_date")
	private Date dueDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_tasks", joinColumns = {
			@JoinColumn(name = "task_id", updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", updatable = false) })
	private Set<User> userSet = new HashSet<>();

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Status getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", content=" + content + ", taskStatus=" + taskStatus
				+ ", dueDate=" + dueDate + ", userSet=" + userSet + "]";
	}

}
