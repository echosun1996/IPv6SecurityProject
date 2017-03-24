<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="handware" class="com.echosun.database.Hardware"
	scope="page" />
<%
	try {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String uid = request.getParameter("uid");
		handware.Hardware_CName(uid, name);
	} catch (Exception e) {
		e.printStackTrace();
		out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp';</script>");
	}
%>
<script type="text/javascript">
	window.location.href = "device_config.jsp";
</script>