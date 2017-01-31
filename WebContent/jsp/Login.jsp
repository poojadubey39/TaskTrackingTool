<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="../static/css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../static/jquery-3.1.1/jquery-3.1.1.js"></script>
<script type="text/javascript" src="../static/jquery-3.1.1/jquery-ui.js"></script>
<link type="text/css" href="../static/jquery-3.1.1/jquery-ui.css" rel="stylesheet">
<script type="text/javascript">

	<%String url = request.getContextPath();%>
	var requestURL = "<%=response.encodeURL(url + "/RequestProcessor")%>"
	$(document).ready(function(){
		$.ajaxSetup({
			  url: requestURL
		});	
		
		openLoginModal();
		$('#userLoginBtn').click(loginUser);
	})

	var openLoginModal = function() {
		$("#loginModal").css("display", "block");
	}
	
	var loginUser = function(){
		var params = {
				"action"	: "login",
				"emailId" 	: $("#emailId").val(),
				"password" 	: $("#password").val()
		}
		
		var xhr = $.ajax({
			data : params,
			type : "POST"
		})
			
		xhr.done(loginResp);
	
	}

	var loginResp = function(response){
		console.log(response);	
		var respJson = $.parseJSON(response);
		if(respJson.hasOwnProperty('error')){
			var error = respJson.error;
			$("#errorTitle").html(error);
			$('#errorMsg').html("Redirecting you to Registration page in ");
			var seconds_left = 10;
			var interval = setInterval(function() {
			    $("#timer").html(seconds_left--+ " seconds")
			    if (seconds_left <= 0)
			    {
			       window.location.href = respJson.redirect; 
			       clearInterval(interval);
			    }
			}, 1000);
			$('.overlay').css('display','block');
			$('.errorBox').css('display','block');
			$('#timer').css('display','block');
		}else{
			 window.location.href = respJson.redirect; 
		}
	}
	
</script>
</head>
<body>
	<!-- <input type="button" value="Login" id="loginBtn" class="btn"
		onclick="openLoginModal()" />
	<input type="button" value="Register" id="registerBtn" class="btn"
		onclick="openRegisterModal()" /> -->

	<div class="modal" id="loginModal">
		<div class="container">
			<div class="modal-content animate" style="height: 50%">
				<label><b>Username</b></label> <br> 
				<input type="email" placeholder="Enter Email Id" id="emailId" required> <br> 
				<label><b>Password</b></label> <br>
				<input type="password" placeholder="Enter Password" id="password" required>
				 <input type="button" value="Login" class="btn"
					id="userLoginBtn" /> <br> <input type="checkbox"
					checked="checked" style="position: relative; left: 4.5%; top: 15%">
				<label style="top: 15%">Remember me</label>
				</div>
			</div>
		</div>
		<div class = "overlay">
			<div id = "errorTitle" class = "errorBox">
			</div>
			<div id = "errorMsg" class = "errorBox">
			</div>
			<div id = "timer"></div>
		</div>
<!-- 		<div class="container" style="background-color: #f1f1f1">
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
 -->
	
</body>
</html>