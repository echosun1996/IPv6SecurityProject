<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.Message" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../Files/echo.css" />
<link href="../Files/style_log.css" rel="stylesheet" type="text/css" />
<title>设备设置</title>
</head>
<body class="site">
	<div class="info" style="text-align: center">
		<b>设备消息</b>
		<table style="margin-left: auto; margin-right: auto; align: center valign:middle">
			<tr>
				<th>设备编号</th>
				<th>消息发送时间</th>
				<th>消息内容</th>
			</tr>
			<%
			try{
				List<Message_Model> res=infos.Message_Sel();
				for(Message_Model i : res){
			%>
			<tr>
				<td><%=i.getUID()%></td>
				<td><%=i.getTime()%></td>
				<td><%=i.getMessage()%></td>
			</tr>
			<%
				}
			}
			catch(Exception e)
			{
				out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp';</script>"); 
			}
			%>
		</table>
	</div>
</body>
</html>