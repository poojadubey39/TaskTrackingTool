package com.practice.tasktracking.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.practice.tasktracking.constants.TaskTrackingConstants;
import com.practice.tasktracking.entities.Task;
import com.practice.tasktracking.entities.User;
import com.practice.tasktracking.enums.Action;
import com.practice.tasktracking.enums.Status;
import com.practice.tasktracking.service.TaskService;
import com.practice.tasktracking.service.UserService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RequestProcessor")
public class RequestProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestProcessorServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		String contextPath = request.getContextPath();
		Map<String, ? super Object> responseMap = new HashMap<>();
		String responseJson;
		Action action = Action.valueOf(request.getParameter("action").toUpperCase());
		switch (action) {

		case LOGIN: {
			
			String emailId = request.getParameter("emailId");
			String password = request.getParameter("password");
			UserService userService = new UserService();
			User user = userService.loginUser(emailId, password);
			if (user != null) {
				String url = contextPath + TaskTrackingConstants.HOME_PAGE_URL;
				request.getSession().setAttribute("user", user);
				responseMap.put("redirect", url);
			} else {
				String url = contextPath + TaskTrackingConstants.REGISTER_PAGE_URL;
				responseMap.put("redirect", url);
				responseMap.put("error", TaskTrackingConstants.USER_NOT_REGISTERED);
			}
			
			System.out.println(responseMap + " = login");
		}
			break;

		case REGISTER: {
			String emailId = request.getParameter("emailId");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			UserService userService = new UserService();
			int id = userService.registerUser(emailId, password, name);
			if (id == -1) {
				String url = contextPath + TaskTrackingConstants.LOGIN_PAGE_URL;
				responseMap.put("error", TaskTrackingConstants.USER_ALREADY_REGISTERED);
				responseMap.put("redirect", url);
			} else {
				User user = userService.findById(id);
				request.getSession().setAttribute("user", user);
				System.out.println("id: "+request.getSession().getId());
				String url = contextPath + TaskTrackingConstants.HOME_PAGE_URL;
				responseMap.put("redirect", url);
			}
		}
			break;

		case RETRIEVE_TASKS: {
			User user = (User) request.getAttribute("user");
			TaskService taskService = new TaskService();
			List<Task> taskList = taskService.retrieveUserTasks(user.getId());
			responseMap.put("taskList", taskList);
		}
			break;

		case CREATE_TASK: {

			String taskName = request.getParameter("taskName");
			String taskContent = request.getParameter("taskContent");
			User user = (User) request.getSession().getAttribute("user");
			TaskService taskService = new TaskService();
			String taskId = taskService.createNewTask(taskName, taskContent, user);
			responseMap.put("taskId", taskId);
		}
			break;

		case CHANGE_STATUS: {

			String taskId = request.getParameter("taskId");
			Status status = Status.valueOf(request.getParameter("status"));
			TaskService taskService = new TaskService();
			taskService.updateStatus(taskId, status);
		}
			break;

		case CHANGE_DUE_DATE: {
			String taskId = request.getParameter("taskId");
			String dueDate = request.getParameter("dueDate");
			Date date = null;
			try {
				date = DateFormat.getInstance().parse(dueDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			TaskService taskService = new TaskService();
			taskService.changeDueDate(taskId, date);
		}
			break;

		case SEARCH_MEMBERS: {
			String searchString = request.getParameter("searchString");
			UserService userService = new UserService();
			List<User> users = userService.searchUsers(searchString);
			// TODO return list
		}
			break;

		case ADD_MEMEBERS: {
			String memberEmailId = request.getParameter("emailId");
			String taskId = request.getParameter("taskId");

			UserService userService = new UserService();
			User user = userService.searchUsers(memberEmailId).get(0);

			TaskService taskService = new TaskService();
			taskService.addMemberToTask(taskId, user);
			// TODO return list
		}
			break;
		default:
			break;
		}
		responseJson = new Gson().toJson(responseMap);
		writer.println(responseJson);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletR equest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

}
