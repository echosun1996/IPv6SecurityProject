<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../Files/echo.css" />
<link href="../Files/style_log.css" rel="stylesheet" type="text/css" />
<title>主窗体</title>
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
		setTimeout("check()", 000);
	}
</script>
</head>
<body class="site" onload="load()">
	<div class="info" style="text-align: center">
		<h2>欢迎进入安全控制台</h2>

		<img id="load" alt="loading……" src="/WebServer/Files/loading.gif" title="正在载入"> <br>
		<p id="info">
			<b>系统自检，请稍候……</b>
		</p>
		<!--  
		<form action="/WebServer/connect" method="post"" >
		<input type="radio" title="aa" value="name"/>启动
		</form>
		-->

	</div>
</body>
</html>