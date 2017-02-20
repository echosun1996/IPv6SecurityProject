<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.Hardware" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./Files/echo.css" />
<link href="./Files/style_log.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(function() {
		$("#flip").click(function() {
			$("#panel").slideToggle("slow");
		});
		setInterval(timer,10000);  
	});
	
	function timer(){
        $.ajax({  
            "url":"/WebServer/Frame/ajax.jsp",  
            "datatype":"json",  
            "type":"post",  
            "async":"true"  
        }).success(function(data){  
            ajaxobj=eval("("+data+")");  
            alert(ajaxobj);
             
              
        }).error();  
    }
</script>
<title>框架标题</title>

</head>
<body class="console">
	<div class="info" style="text-align: center">
		<div id="flip">
			<b>启动服务</b>
		</div>

		<div id="panel" style="display: none; background-color: pink; width: 552px;">


			<div style="float: left; background-color: white; width: 400px; height: 300px;">
				<b>入侵警告</b>
			</div>

			<div style="float: right; background-color: green; width: 150px; height: 100px;">
				<p>传感器个数</p>
				<b>5</b>
			</div>

			<div style="float: right; background-color: blue; width: 150px; height: 100px;">
				<p>←警告清空</p>
			</div>

			<div style="float: right; background-color: green; width: 150px; height: 100px;">
				<p>↓消息清空</p>
			</div>

			<div style="float: left; background-color: blue; width: 552px; height: 400px;">
				<b>系统消息</b>
			</div>

		</div>
	</div>
</body>
</html>