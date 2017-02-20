<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%session.removeAttribute("loginuser");	%>
<%
    out.println("<html>");      
    out.println("<script>");      
    out.println("window.open ('"+request.getContextPath()+"/index.jsp','_top')");      
    out.println("</script>");      
    out.println("</html>");    
%>
