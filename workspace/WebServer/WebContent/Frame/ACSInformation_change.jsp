<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="ACSInformation"
	class="com.echosun.database.ACSInformation" scope="page" />
<%
	try {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String cname_id = request.getParameter("cname_id");
		if (cname_id != null) {
			String name = request.getParameter("name");
			ACSInformation.ACSInformation_CName(cname_id, name);
		}
		String csta_id = request.getParameter("csta_id");
		if (csta_id != null)
			ACSInformation.ACSInformation_CSta(csta_id);
	} catch (Exception e) {
		e.printStackTrace();
		out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp';</script>");
	}
%>
<script type="text/javascript">
	window.location.href = "ACSInformation.jsp";
</script>