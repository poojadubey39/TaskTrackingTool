<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Tracking Tool - Home</title>
<link href="../static/css/homePage.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../static/jquery-3.1.1/jquery-3.1.1.js"></script>
<script type="text/javascript" src="../static/jquery-3.1.1/jquery-ui.js"></script>
<link type="text/css" href="../static/jquery-3.1.1/jquery-ui.css" rel="stylesheet">
<script type="text/javascript">

$(document).ready(function(){
	createTaskPage();
});
<%String url = request.getContextPath();%>
var requestURL = "<%=response.encodeURL(url + "/RequestProcessor")%>"

function createTaskPage(){
	for(var i = 0; i <= 4; i++){
		
		new Task(i, "Task "+i, "TaskContent "+i, "NEW").display();;
	}
}

function Task(id, name, content, status){
	this.id = id;
	this.name = name;
	this.content = content;
	this.status = status;
	this.display = function(){
		var node = document.body;
		var newNode = document.createElement('div');
		newNode.setAttribute("id",id);	
		var titleNode = document.createElement('div');
		var contentNode = document.createElement('div');
		titleNode.appendChild(document.createTextNode(name));
		contentNode.appendChild(document.createTextNode(content));
		newNode.appendChild(titleNode);
		newNode.appendChild(contentNode);
		node.appendChild(newNode);
		$("#"+id).addClass("taskDiv");
		titleNode.setAttribute("class","taskTitle");
		contentNode.setAttribute("class","taskContent");
	}
	
}

</script>
</head>
<body>
</body>
</html>