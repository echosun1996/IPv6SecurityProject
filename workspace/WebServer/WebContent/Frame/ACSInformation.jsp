<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.echosun.database.*"%>
<jsp:useBean id="infos" class="com.echosun.database.ACSInformation"
	scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../Files/echo.css" />
<link href="../Files/style_log.css" rel="stylesheet" type="text/css" />
<title>ACSInformation</title>
</head>
<body class="site">
	<div class="info" style="text-align: center">
		<b>设备消息</b>
		<table
			style="margin-left: auto; margin-right: auto; align: center valign:middle">
			<tr>
				<th>门禁卡编号</th>
				<th>名称</th>
				<th>状态</th>
				<th>录入时间</th>
			</tr>
			<%
				try {
					List<ACSInformation_Model> res = infos.ACSInformation_SelALL();
					for (ACSInformation_Model i : res) {
			%>
			<tr>

				<td><%=i.getID()%></td>
				<td>
				
					<form action="ACSInformation_change.jsp" method="post">
					<input type="text" value="<%=i.getName()%>" name="name" />
						<input type="hidden" name="cname_id" value="<%=i.getID()%>"> <input
							type="image" alt="提交" title="提交" src="../Files/refresh.png"
							width="23" height="12" onclick="submit()" />
					</form>
				</td>
				<td><%=i.getStatus()%>
				<form action="ACSInformation_change.jsp" method="post">
						<input type="hidden" name="csta_id" value="<%=i.getID()%>"> <input
							type="image" alt="提交" title="提交" src="../Files/refresh.png"
							width="23" height="12" onclick="submit()" />
				</form>
				</td>
				<td><%=i.getTime()%></td>
				

			</tr>
			<%
				}
				} catch (Exception e) {
					out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp';</script>");
				}
			%>
		</table>
	</div>
</body>
</html>