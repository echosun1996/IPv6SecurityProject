<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.Hardware" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../Files/echo.css" />
<link href="../Files/style_log.css" rel="stylesheet" type="text/css" />
<title>设备设置</title>
<script type="text/javascript">
</script>
</head>
<body class="site">
	<div class="info" style="text-align: center">
		<b>设备设置</b>
		<table style="margin-left: auto; margin-right: auto; align: center valign:middle">
			<tr>
				<th>设备编号</th>
				<th>设备备注(单击刷新更新资料)</th>
				<th>设备类别</th>
				<th>设备状态</th>
				
			</tr>
			<%
			try{
				List<Hardware_Model> res=infos.Hardware_Sel();
				for(Hardware_Model i : res){
			%>
			<tr>
				
				<td><%=i.getUID()%></td>
				<td>
					<form action="device_change.jsp" method="post">
						<input type="hidden" name="uid" value="<%=i.getUID()%>">
						<input type="text" value="<%=i.getName()%>" name="name"/>
						 <input type="image" alt="提交" title="提交" src="../Files/refresh.png" width="23" height="12" onclick="submit()"/> 
					</form>
				<td><%=i.getCategory()%></td>
				<td><%=i.getStatus()%></td>
				
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