<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.Hardware"
	scope="page" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>启动服务</title>
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

<script>
	//SSE
	var warn = new EventSource("/WebServer/SSEWarning");
	warn.addEventListener("message", function(e1) {
		document.getElementById("warning").innerHTML = e1.data;
	}, false);

	var mess = new EventSource("/WebServer/SSEMessage");
	mess.addEventListener("message", function(e2) {
		document.getElementById("message").innerHTML = e2.data;
	}, false);

	var SSESum = new EventSource("/WebServer/SSESum");
	SSESum.addEventListener("message", function(e2) {
		document.getElementById("sum").innerHTML = e2.data;
	}, false);
</script>
<script>
	window.status = 0;
	$(document).ready(function() {
		getstatus();
		//clear_error
		$("#clear_error").click(function() {
			$.post("/WebServer/Frame/ask.jsp", {
				"ask" : "clear_error",
			}, function(data, status) {
				var obj = eval("(" + data + ")");
				if (obj.sta != 1) {
					alert('数据库连接异常！请联系管理员');
					window.location.href = 'main.jsp?error';
				}
				window.status = obj.Switch;

			});
		});

		$("#clear_message").click(function() {
			$.post("/WebServer/Frame/ask.jsp", {
				"ask" : "clear_message",
			}, function(data, status) {
				var obj = eval("(" + data + ")");
				if (obj.sta != 1) {
					alert('数据库连接异常！请联系管理员');
					window.location.href = 'main.jsp?error';
				}
				window.status = obj.Switch;

			});
		});

		$("#flip").click(function() {
			setTimeout('$("#flip").disabled=true', 5000);
			$("#flip").disabled = false;
			if (window.status == 0) {
				open();
				$("#flip").html("<h3>关闭服务</h3>");
			} else {
				close();
				$("#flip").html('<h3>启动服务</h3>');
			}

		});

	});
	function getstatus() {
		$.post("/WebServer/Frame/ask.jsp", {
			"ask" : "status",
		}, function(data, status) {
			var obj = eval("(" + data + ")");
			if (obj.sta != 1) {
				alert('数据库连接异常！请联系管理员');
				window.location.href = 'main.jsp?error';
			}
			window.status = obj.Switch;
			if (obj.Switch == 1) {
				$("#flip").html("<h3>关闭服务</h3>");
				$("#panel").slideDown("slow");
			}

			else if (obj.Switch == 0) {
				$("#flip").html("<h3>启动服务</h3>");
				$("#panel").slideUp("slow");
			}
		});
	}
	function close() {
		$.post("/WebServer/connect", {
			"status" : "0",
		}, function(data, status) {
			var obj = eval("(" + data + ")");
			if (obj.sta != 1) {
				alert('数据库连接异常！请联系管理员');
				window.location.href = 'main.jsp?error';
			} else if (obj.Switch == 0) {
				window.status = 0;

				$.post("/WebServer/Frame/ask.jsp", {
					"ask" : "close",
				}, function(data, status) {
					var obj = eval("(" + data + ")");
					if (obj.sta != 1) {
						alert('数据库连接异常！请联系管理员');
						window.location.href = 'main.jsp?error';
					} else if (obj.ret == 0) {
						$("#panel").slideUp("slow");
					}
				});
			}

		});
	}

	function open() {
		$.post("/WebServer/connect", {
			"status" : "1",
		}, function(data, status) {
			var obj = eval("(" + data + ")");
			if (obj.sta != 1) {
				alert('数据库连接异常！请联系管理员');
				window.location.href = 'main.jsp?error';
			} else if (obj.Switch == 1) {
				window.status = 1;

				$.post("/WebServer/Frame/ask.jsp", {
					"ask" : "open",
				}, function(data, status) {
					var obj = eval("(" + data + ")");
					//alert(obj.Switch);
					if (obj.sta != 1) {
						alert('数据库连接异常！请联系管理员');
						window.location.href = 'main.jsp?error';
					} else if (obj.ret == 1) {
						$("#panel").slideDown("slow");
					}
				});

			}
		});

	}
</script>
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
					<li class="active"><a href="main.jsp"><span
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

					<li><a href="user_config.jsp" id="user_config"><span
							class="glyphicon glyphicon-user"></span>&nbsp; 用户设置</a></li>
					<li><a href="/WebServer/loginout.jsp" id="loginout"><span
							class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;
							退出登录</a></li>
				</ul>
			</div>

			<div class="col-xs-12 col-sm-9 col-md-10 info">

				<button type="button" class="btn btn-default btn-lg btn-block"
					id="flip">
					<h3>启动服务</h3>
				</button>

				<div id="panel">


					<div class="table-responsive">
						<h4 class="text-center">设备警报</h4>

						<div style="overflow-x: auto; overflow-y: auto; height: 200px;"
							class="table-responsive" id="warning"></div>
						<button type="button" id="clear_error"
							class="btn btn-primary pull-right">警告清空</button>
					</div>


					<div class="">
						<p>
							传感器个数: <b id="sum">0</b>
						</p>

					</div>





					<div class="table-responsive">
						<h4 class="text-center">未读消息</h4>

						<div style="overflow-x: auto; overflow-y: auto; height: 200px;"
							class="table-responsive" id="message">
							<table class="table table-condensed">

								<thead>
									<tr>
										<th>设备编号</th>
										<th>设备备注</th>
										<th>设备类别</th>
										<th>设备状态</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
						<button type="button" id="clear_message"
							class="btn btn-primary pull-right">消息清空</button>





					</div>


				</div>
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