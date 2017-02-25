<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.Hardware"
	scope="page" />
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/WebServer/Files/echo.css" />
<link href="/WebServer/Files/style_log.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

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
	
	
	var mess = new EventSource("/WebServer/SSESum");  
	mess.addEventListener("message", function(e2) {
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
			setTimeout('$("#flip").disabled=true',5000);
			$("#flip").disabled=false;
			if (window.status == 0) {
				open();
			} else {
				close();
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
				$("#panel").slideDown("slow");
			}

			else if (obj.Switch == 0) {
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
<title>框架标题</title>

</head>
<body class="console">
	<div class="info" style="text-align: center">
		<div id="flip">
			<b>启动服务</b>
		</div>

		<div id="panel" style="display: none; width: 552px;">


			<div id="warning"
				style="float: left; background-color: #9966CC; width: 400px; height: 300px;">
				<b>入侵警告</b>
			</div>


			<div id="clear_error"
				style="float: right; background-color: #FF9933; width: 150px; height: 100px;">
				<p>←警告清空</p>
			</div>

			<div
				style="float: right; background-color: #9966CC; width: 150px; height: 100px;">
				<p>传感器个数</p>
				<b id="sum">0</b>
			</div>

			<div id="clear_message"
				style="float: right; background-color: #FF9933; width: 150px; height: 100px;">
				<p>↓消息清空</p>
			</div>

			<div id="message"
				style="float: left; background-color: #9966CC; margin-top: 3px; width: 552px; height: 400px; border-top: 20px;">
				<b>系统消息</b>
			</div>

		</div>
	</div>
</body>
</html>