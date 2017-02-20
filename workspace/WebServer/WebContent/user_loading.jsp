<%@page import="com.google.code.kaptcha.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="user" class="com.echosun.login.User" scope="request" />
<jsp:useBean id="login" class="com.echosun.login.Login_check" scope="request" />
<jsp:setProperty name="user" property="*" />
<%
//String pass=user.getPassword();
//out.println("<p>pass:"+pass+"\\</p>");
//String check_sum=user.getRandomString();
//String check_sum_right=null;
//if(session.getAttribute("check")!=null)
//	check_sum_right=(String)session.getAttribute("check");
//out.println("<p>checksum------>|"+check_sum+"|----+++---|"+check_sum_right+"</p>");
try{
if(session.getAttribute("loginuser")!=null)
{
	response.sendRedirect("login_success.jsp");
}
else if(request.getParameter("check")==null)
{
	out.print("<script>alert('非法操作！');window.location.href='index.jsp';</script>");  
}
else if(!((String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY)).equals(request.getParameter("checknum")))
{
	out.print("<script>alert('验证码错误！');window.location.href='index.jsp';</script>");  
}
else if(!login.UserLogin(user,(String)request.getParameter("check")))//传入用户名/密文 验证通过
{
	out.print("<script>alert('用户名或密码错误！');window.location.href='index.jsp';</script>");  
}
else
{
	String[] save_me=request.getParameterValues("save_me");
	//remember me点击判断
	if(save_me!=null&&save_me.length>0)
	{
		String username=user.getUsername();
		Cookie usernameCookie=new Cookie("username",username);//添加user cookie字段
		usernameCookie.setMaxAge(864000);
		response.addCookie(usernameCookie);
		out.println("click");
	}
	else
	{
		Cookie[] cookies=request.getCookies();
		if(cookies!=null&&cookies.length>0)
		{
			for(Cookie c:cookies)
			{
				if(c.getName().equals("username"))
				{
				c.setMaxAge(0);
				response.addCookie(c);
				break;
				}
			}
		}
	}
	session.setAttribute("loginuser", user.getUsername());
	//out.println("true");
	//正常跳转
	response.sendRedirect("login_success.jsp");
	//内部转发跳转
	//request.getRequestDispatcher("login_success.jsp").forward(request, response);
}
}
catch(Exception e)
{
	e.printStackTrace();
	out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='/WebServer/index.jsp';</script>"); 
}
%>