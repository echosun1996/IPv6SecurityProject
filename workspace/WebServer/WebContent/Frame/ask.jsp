<%@page import="com.echosun.database.Message"%>
<%@page import="com.echosun.database.Hardware"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.echosun.database.DBSystem_Model"%>
<jsp:useBean id="system" class="com.echosun.database.DBSystem"
	scope="page" />
<%@page import="java.util.List"%>
<%
	String ask = request.getParameter("ask");
	if (ask.equals("status")) {
		try {
			String Switch = null;
			List<DBSystem_Model> ress = system.System_Sel();
			for (DBSystem_Model res : ress) {
				if ("Switch".equals(res.getKey())) {
					Switch = res.getValue();
					break;
				}
			}
			if (Switch == null) {
				DBSystem_Model tem = new DBSystem_Model();
				tem.setKey("Switch");
				tem.setValue("0");
				system.System_Add(tem);
				Switch = "0";
			}
			out.print("{\"sta\": 1,\"Switch\": " + Switch + "}");
			//out.print(Switch);
			//request.setAttribute("Switch", Switch);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"sta\": 0,\"Switch\": -1}");
			out.print("error");
			//out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp?error';</script>");
		}
	}

	else if (ask.equals("close")) {
		try {
			String Switch = null;
			List<DBSystem_Model> ress = system.System_Sel();
			for (DBSystem_Model res : ress) {
				if ("Switch".equals(res.getKey())) {
					Switch = res.getValue();
					break;
				}
			}
			if (Switch == null) {
				DBSystem_Model tem = new DBSystem_Model();
				tem.setKey("Switch");
				tem.setValue("0");
				system.System_Add(tem);
				Switch = "0";
			}

			system.System_Upd("Switch", "0");

			out.print("{\"sta\": 1,\"ret\": " + "0" + "}");
			//out.print(Switch);
			//request.setAttribute("Switch", Switch);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"sta\": 0,\"ret\": -1}");
			out.print("error");
			//out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp?error';</script>");
		}
	}

	else if (ask.equals("open")) {
		try {
			String Switch = null;
			List<DBSystem_Model> ress = system.System_Sel();
			for (DBSystem_Model res : ress) {
				if ("Switch".equals(res.getKey())) {
					Switch = res.getValue();
					break;
				}
			}
			if (Switch == null) {
				DBSystem_Model tem = new DBSystem_Model();
				tem.setKey("Switch");
				tem.setValue("1");
				system.System_Add(tem);
				Switch = "1";
			}

			system.System_Upd("Switch", "1");

			out.print("{\"sta\": 1,\"ret\": " + "1" + "}");
			//out.print(Switch);
			//request.setAttribute("Switch", Switch);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"sta\": 0,\"ret\": -1}");
			out.print("error");
			//out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp?error';</script>");
		}		
	}
	
	
	else if (ask.equals("clear_error")) {
		try {
			Hardware hw=new Hardware();
			hw.Hardware_SetNormal();
			out.print("{\"sta\": 1,\"ret\": " + "1" + "}");
			//out.print(Switch);
			//request.setAttribute("Switch", Switch);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"sta\": 0,\"ret\": -1}");
			out.print("error");
			//out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp?error';</script>");
		}
	}
	
	else if (ask.equals("clear_message")) {
		try {
			Message ms=new Message();
			ms.Message_SetNormal();
			out.print("{\"sta\": 1,\"ret\": " + "1" + "}");
			//out.print(Switch);
			//request.setAttribute("Switch", Switch);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"sta\": 0,\"ret\": -1}");
			out.print("error");
			//out.print("<script>alert('数据库连接异常！请联系管理员');window.location.href='main.jsp?error';</script>");
		}
	}
	
%>