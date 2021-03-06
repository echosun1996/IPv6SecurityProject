<%@page import="org.apache.catalina.deploy.LoginConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="random" class="com.echosun.login.RandomGenerator"
	scope="request" />
<%
	random.RandomString();
	String cookie_username = "Your name";
	Cookie[] cookies = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (Cookie c : cookies) {
			if (c.getName().equals("username")) {
				cookie_username = c.getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/WebServer/Files/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />

<title>欢迎登陆</title>
<link rel="stylesheet" type="text/css" href="/WebServer/Files/style.css" />
<link href="/WebServer/Files/style_log.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="/WebServer/Files/sha1.js"></script>
<script type="text/javascript">
	function refresh() {
		var randomnumber = Math.random();
		var checkimg = document.getElementById("check_img");
		var check = document.getElementById("check");
		checkimg.src = "/WebServer/authImage?" + randomnumber;
		check.value="<%=random.getRandomString()%>";
	}
	//check.value = check.value.toLowerCase();
	function sub() {
		var passwd = document.getElementById("userpwd");
		var name = document.getElementById("username");
		var checknum = document.getElementById("checknum");
		var check = document.getElementById("check");
		var pass = 1;
		if (name.value == "Your name") {
			name.style.border = "1px solid #a71443";
			pass = 0;
		}
		if (passwd.value == "******") {
			passwd.style.border = "1px solid #a71443";
			pass = 0;
		}
		if (checknum.value == "Check number") {
			checknum.style.border = "1px solid #a71443";
			pass = 0;
		}
		if (pass == 0)
			return false;
		var salt = "f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
		passwd.value = hex_sha1(hex_sha1(passwd.value + salt) + check.value);
		return true;
	}
</script>
</head>
<body class="login" onload="refresh()">
	<form action="user_loading.jsp" onsubmit="return sub()" method="post">

		<div class="login_m">
			<div class="login_logo">
				<img src="/WebServer/Files/logo.png" />
			</div>
			<div class="login_boder">
				<div class="login_padding" id="login_model">
					<h2>用户名</h2>
					<label> <input type="text" id="username" name="username"
						class="txt_input txt_input2" onmouseover="" onmouseout=""
						onfocus="this.style.border='1px solid #cad2db'; if (value =='Your name'){value ='';}"
						onblur="if (value ==''){value='Your name';}"
						value="<%=cookie_username%>" /></label>
					<h2>密码</h2>
					<label> <input type="password" name="password" id="userpwd"
						class="txt_input txt_input2" onmouseover="" onmouseout=""
						onfocus="this.style.border='1px solid #cad2db';if (value =='******'){value ='';}"
						onblur="if (value ==''){value='******';}" value="******" /></label>
					<h2>验证码</h2>
					<label> <input type="text" id="checknum" name="checknum"
						class="check" onmouseover="" onmouseout=""
						onfocus="if (value =='Check number'){value ='';}"
						onblur="this.style.border='1px solid #cad2db'; if (value ==''){value='Check number';}"
						value="Check number" /></label> <img id="check_img" src="authImage"
						width="110px" height="38px" style="vertical-align: middle;"
						onclick="refresh()" /> <input type="hidden" id="check"
						name="check">
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" name="save_me" id="save_me" /><label
								for="save_me">Remember me</label>
						</div>
						<label> <input type="submit" class="sub_button"
							name="button" id="button" value="SIGN-IN" style="opacity: 0.7;" /></label>
					</div>
				</div>
			</div>
		</div>
	</form>

	<br />
	<br />
</body>
</html>