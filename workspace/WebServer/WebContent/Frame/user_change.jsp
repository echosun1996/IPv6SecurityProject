<%@page import="javax.websocket.Session"%>
<%@page import="com.echosun.login.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="login" class="com.echosun.login.Login_check" scope="page" />
<jsp:useBean id="logindb" class="com.echosun.database.Login" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
try{
User old=new User();
old.setPassword(request.getParameter("oldpasswd"));
old.setUsername((String)session.getAttribute("loginuser"));
//out.print(old.getPassword()+"++++"+old.getUsername()+"++++"+request.getParameter("passwd"));
if(!login.UserLogin(old,(String)session.getAttribute("check2")))//传入用户名/密文 验证通过
{
	out.print("<script>alert('密码错误！'); window.location.href='user_config.jsp'; </script>");
} 
else 
{
	logindb.Login_Upd(old.getUsername(), request.getParameter("passwd"));
	
	
	out.print("<script>alert('密码修改成功！请重新登陆！'); window.location.href='../loginout.jsp';</script>");
}
}
catch(Exception e)
{
	out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp';</script>"); 
}
%>
