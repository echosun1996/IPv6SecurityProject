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
<title>框架标题</title>
</head>
<body class="site">
	<div class="info" style="text-align: center">
		<b>Hardware_Information演示</b>
		<table style="margin-left: auto; margin-right: auto; align: center valign:middle">
			<tr>
				<th>设备编号</th>
				<th>设备备注</th>
				<th>设备类别</th>
				<th>设备状态</th>
			</tr>
			<%
				List<Hardware_Model> res=infos.Hardware_Sel();
									  for(Hardware_Model i : res){
			%>
			<tr>
				<td><%=i.getUID()%></td>
				<td>
					<form action="Hardware_update.jsp" method="post">
						<input value="<%=i.getName()%>" />
						 <img alt="" id="loginin" src="Files/loading1.gif" width="57" height="20" onclick="document.login.submit()"/> 
					</form>
				<td><%=i.getCategory()%></td>
				<td><%=i.getStatus()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>