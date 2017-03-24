<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>主窗体</title>
<link rel="icon" href="/WebServer/Files/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="/WebServer/Files/favicon.ico"
	type="image/x-icon" />


<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">


<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript">
	function check() {
		if (window.location.search == "?error") {
			var info = document.getElementById("info");
			info.innerHTML = "<b>服务器连接失败，请稍后重试</b>";
		} else {
			location.href = "console.jsp"
		}
	}
	function load() {
		setTimeout("check()", 200);
	}
</script>
</head>
<body onload="load()">
	<div class="container-fluid">
		<h1 class="text-center">安全控制台</h1>
	</div>
	<hr
		style="height: 3px; border: none; border-top: 3px groove deepskyblue;" />






	<div class="container-fluid">
		<div class="row">


			<div class="span2  col-xs-12 col-sm-3 col-md-2">
				<ul class="nav nav-pills nav-stacked demo-nav side-bar">
					<li class="active"><a href="main.jsp" target="main"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;
							面板首页</a></li>
					<li><a href="device_config.jsp" target="main"
						id="device_config"><span class="glyphicon glyphicon-cog"
							aria-hidden="true"></span>&nbsp; 设备设置</a></li>
					<li><a href="device_message.jsp" target="main"
						id="device_message"><span class="glyphicon glyphicon-envelope"
							aria-hidden="true"></span>&nbsp; 设备消息 </a></li>
					<li><a href="ACSInformation.jsp" target="main"
						id="ACSInformation"><span class="glyphicon glyphicon-wrench"
							aria-hidden="true"></span>&nbsp; 门禁设置</a></li>

					<li><a href="user_config.jsp" target="main" id="user_config"><span
							class="glyphicon glyphicon-user"></span>&nbsp; 用户设置</a></li>
					<li><a href="/WebServer/loginout.jsp" id="loginout"><span
							class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;
							用户登出</a></li>
				</ul>
			</div>

			<div class="col-xs-12 col-sm-9 col-md-10 text-center">
				<h2>欢迎进入安全控制台</h2>




				<div class="progress cus-progress">
					<div class="progress-bar progress-bar-striped active"
						role="progressbar" aria-valuenow="45" aria-valuemin="0"
						aria-valuemax="100" style="width: 100%"></div>
				</div>







				<br>
				<p id="info">
					<b>系统自检，请稍候……</b>
				</p>



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