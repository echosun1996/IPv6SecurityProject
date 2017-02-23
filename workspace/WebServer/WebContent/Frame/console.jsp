<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.echosun.database.DBSystem_Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<jsp:useBean id="system" class="com.echosun.database.DBSystem" scope="page" />
<%
	try
		{
		String Switch=null;
		List<DBSystem_Model> ress=system.System_Sel();
		for(DBSystem_Model res:ress)
		{
		if("Switch".equals(res.getKey()))
		{
		Switch=res.getValue();
		break;
		}
		}
		if(Switch==null)
		{
		DBSystem_Model tem=new DBSystem_Model();
		tem.setKey("Switch");
		tem.setValue("close");
		system.System_Add(tem);
		Switch="close";
		}
		/*
		if(Switch=="open")
	{
	request.setAttribute("Switch", "系统启动");
	
	}
		else
	request.setAttribute("Switch", "系统关闭");
		*/
		}
	catch(Exception e)
	{
		e.printStackTrace();
		out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp?error';</script>"); 
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../Files/echo.css" />
<link href="../Files/style_log.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#start_button")
								.click(
										function() {
											$("#div1").fadeIn();
											$("#div2").fadeIn("slow");
											$("#div3").fadeIn(3000);

											var request = null;
											if (window.XMLHttpRequest) {
												request = new XMLHttpRequest();
											} else
												request = new ActiveXObject(
														"MIcrosoft.XMLHTTP");
											request.open("post", "ajax.jsp",
													true);
											request
													.setRequestHeader(
															'Content-type',
															'application/x-www-form-urlencoded');
											var data = "name=1&body=2"
											request.send(data);
											request.onreadystatechange = function() {
												if (request.readyState === 4
														&& request.status === 200) {
													alert("123");
												}
											}

										});

					});
</script>
<script type="text/javascript">
	
</script>

</head>

<body class="site">

	<div class="info">
		<button id="start_button">${requestScope.Switch}</button>
		<div id="div1" style="width: 80px; height: 80px; display: none; background-color: red;"></div>
		<br>
		<div id="div2" style="width: 80px; height: 80px; display: none; background-color: green;"></div>
		<br>
		<div id="div3" style="width: 80px; height: 80px; display: none; background-color: blue;"></div>




		<json:object>
			<json:property name="itemCount" value="${cart.itemCount}" />
			<json:property name="subtotal" value="${cart.subtotal}" />
			<json:array name="items" var="item" items="${cart.lineItems}">
				<json:object>
					<json:property name="title" value="${item.title}" />
					<json:property name="description" value="${item.description}" />
					<json:property name="price" value="${item.price}" />
					<json:property name="qty" value="${item.qty}" />
				</json:object>
			</json:array>
		</json:object>
	</div>
</body>
</html>