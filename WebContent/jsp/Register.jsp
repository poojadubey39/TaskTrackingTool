<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href="../static/css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../static/jquery-3.1.1/jquery-3.1.1.js"></script>
<script type="text/javascript">

<%String url = request.getContextPath();%>
var requestURL = "<%=response.encodeURL(url + "/RequestProcessor")%>"
$(document).ready(function(){

	openRegisterModal();
	$('#registerUserBtn').click(registerUser);
	
	})

	var openRegisterModal = function() {
		$("#registerModal").css("display", "block");
	}
	
	var registerUser = function(){
		
		var params = {
				"action" : "register",
				"name" : $('#name').val(),
				"emailId" : $('#emailId').val(),
				"password" : $('#password').val()
		}
		
		var xhr = $.ajax(requestURL, {
			data : params,
			type : "POST"
		})
		
		xhr.done(registrationResp);
	}
	
	var registrationResp = function(response){
		var respJson = $.parseJSON(response);
		if(respJson.hasOwnProperty('error')){
			
		}else{
			window.location.href = respJson.redirect;
		}
	}

</script>
</head>
<body>
<div class="modal" id="registerModal">
		<div class="container">
			<div class="modal-content animate">
				<label><b>Name</b></label> <br> 
				<input type="text" placeholder="Enter Name" required id="name"> <br> 
				<label><b>Password</b></label> <br>
				<input type="password" placeholder="Enter Password" required id="password"><br>
				<label><b>Email</b></label> <br>
				<input type="email" placeholder="Enter EmailId" required id="emailId"> <br>
				<input type="button" value="Register" class="btn"id="registerUserBtn"/> <br>
			</div>
		</div>
		<div class="container" style="background-color: #f1f1f1">
			<!-- <span class="psw">Forgot <a href="#">password?</a></span> -->
		</div>
	</div>
</body>
</html>