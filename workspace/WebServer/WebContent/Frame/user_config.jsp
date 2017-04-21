<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="random" class="com.echosun.login.RandomGenerator"
	scope="page" />
<%
	random.RandomString();
	session.setAttribute("check2", random.getRandomString());
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>安全控制台</title>
<link rel="icon" href="/WebServer/Files/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />


<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
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

<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	<div class="container-fluid">
		<h1 class="text-center">安全控制台</h1>
	</div>
	<hr
		style="height: 3px; border: none; border-top: 3px groove deepskyblue;" />

	<div class="container-fluid">
		<div class="row">
			<div class="span2  col-xs-12 col-sm-3 col-md-2">
				<ul class="nav nav-pills nav-stacked demo-nav side-bar">
					<li><a href="main.jsp"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;
							面板首页</a></li>
					<li><a href="device_config.jsp" id="device_config"><span
							class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;
							设备设置</a></li>
					<li><a href="device_message.jsp" id="device_message"><span
							class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;
							设备消息 </a></li>
					<li><a href="ACSInformation.jsp" id="ACSInformation"><span
							class="glyphicon glyphicon-wrench" aria-hidden="true"></span>&nbsp;
							门禁设置</a></li>

					<li class="active"><a href="user_config.jsp" id="user_config"><span
							class="glyphicon glyphicon-user"></span>&nbsp; 用户设置</a></li>
					<li><a href="/WebServer/loginout.jsp" id="loginout"><span
							class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;
							退出登录</a></li>
				</ul>
			</div>

			<div class="col-xs-12 col-sm-9 col-md-10">
				<h2 class="text-center">修改密码</h2>
				<form action="user_change.jsp" method="post" onsubmit="return sub()">
					<div class="form-group">
						<label for="exampleInputEmail1">旧的密码</label> <input
							type="password" class="form-control cus-formcontrol"
							id="oldpasswd" name="oldpasswd" placeholder="Old Password">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">新的密码</label> <input
							type="password" class="form-control cus-formcontrol" id="passwd"
							name="passwd" placeholder="New Password">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">重复输入</label> <input
							type="password" class="form-control cus-formcontrol"
							id="repasswd" name="repasswd" placeholder="Repeat Password">
					</div>


					<button type="submit" class="btn btn-default">提交</button>
					<button type="reset" class="btn btn-default">清空</button>
				</form>







			</div>

		</div>
		<hr class="divider">
		<footer>
			<p class="pull-right">
				<a href="#top">回到顶部</a>
			</p>
			<p>Copyright 2017 IPv6SecurityProject. All Rights Reserved.</p>
		</footer>

	</div>











	<script src="../js/jquery-1.11.1.min.js"></script>

	<script src="../js/bootstrap.min.js"></script>
</body>
</html>