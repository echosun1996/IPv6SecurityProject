<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="random" class="com.echosun.login.RandomGenerator" scope="page" />
<%
	random.RandomString();
	session.setAttribute("check2", random.getRandomString());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主窗体</title>
<link rel="stylesheet" type="text/css" href="../Files/echo.css" />
<link href="../Files/style_log.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Files/sha1.js"></script>
<script type="text/javascript">
	function sub() {
		var oldpasswd = document.getElementById("oldpasswd");
		var passwd = document.getElementById("passwd");
		var repasswd = document.getElementById("repasswd");
		if(passwd.value ==""||oldpasswd==""||repasswd=="")
			{
			alert("密码不能为空！请重新输入！");
			oldpasswd = ""
			passwd.value = "";
			repasswd.value = "";
			return false;
			}
		else if (passwd.value != repasswd.value) {
			alert("两次密码不匹配！请重新输入！");
			oldpasswd = ""
			passwd.value = "";
			repasswd.value = "";
			return false;
		} else {
			var salt="f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
			oldpasswd.value =hex_sha1(hex_sha1(oldpasswd.value+salt)+"<%=random.getRandomString()%>");
			passwd.value = hex_sha1(passwd.value + salt);
			return true;
		}
	}
</script>
</head>
<body class="site">
	<form action="user_change.jsp" method="post" onsubmit="return sub()">
		<div class="info">
		<div class="login">
				<div class="login_padding" id="login_model">
			<h2>旧的密码</h2>
			<label><input type="password" id="oldpasswd" name="oldpasswd" /></label>
			<h2>新的密码</h2>
			<label><input type="password" id="passwd" name="passwd" /></label>
			<h2>重复输入</h2>
			<label><input type="password" id="repasswd" name="repasswd" /></label>
			<br>
			<label><input type="submit" value="提交" /></label> 
			<label><input type="reset" value="清空" /></label>
		</div>
		</div>
		</div>
	</form>
</body>
</html>