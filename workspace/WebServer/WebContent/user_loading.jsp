<%@page import="com.google.code.kaptcha.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="user" class="com.echosun.User" scope="page" />
<jsp:useBean id="login" class="com.echosun.Login" scope="page" />
<jsp:setProperty name="user" property="*" />
<%
//String pass=user.getPassword();
//out.println("<p>pass:"+pass+"\\</p>");
//String check_sum=user.getRandomString();
//String check_sum_right=null;
//if(session.getAttribute("check")!=null)
//	check_sum_right=(String)session.getAttribute("check");
//out.println("<p>checksum------>|"+check_sum+"|----+++---|"+check_sum_right+"</p>");
if(!((String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY)).equals(request.getParameter("check")))
{
	out.print("<h2>验证码错误！</h2>");
}
else if(!login.UserLogin(user,(String)session.getAttribute("check")))//传入用户名/密文 验证通过
{
	out.print("<h2>用户名或密码错误！</h2>");
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
	request.getRequestDispatcher("login_success.jsp").forward(request, response);
}
%>