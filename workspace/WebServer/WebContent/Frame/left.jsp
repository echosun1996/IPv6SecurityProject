<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Object obj = session.getAttribute("loginuser");  
if (obj == null) {  
    out.println("<html>");      
    out.println("<script>");      
    out.println("window.open ('"+request.getContextPath()+"/index.jsp','_top')");      
    out.println("</script>");      
    out.println("</html>");    
}  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>侧边栏</title>
<link rel="stylesheet" type="text/css" href="../Files/echo.css" />
<link href="../Files/style_log.css" rel="stylesheet" type="text/css" />
</head>
<body class="site">
<div style="float: right;text-align: right;">
	<a href="main.jsp" target="main">面板首页</a>
	<br>
	<a href="device_config.jsp" target="main" id="device_config">设备设置</a>
	<br>
	<a href="device_message.jsp" target="main" id="device_config">设备消息</a>
	<br>
	<a href="user_config.jsp" target="main" id="device_message">用户设置</a>
	<br>
	<a href="/WebServer/loginout.jsp" id="device_message">用户登出</a>
	<br>
</div>
</body>
</html>