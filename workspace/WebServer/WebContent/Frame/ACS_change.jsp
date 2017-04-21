<%@page import="com.echosun.database.ACSInformation"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ask = request.getParameter("ask");
	String id = request.getParameter("id");
	int ret = 1;
	int ret_sta = 0;
	if (ask.equals("change")) {
		String sta = request.getParameter("sta");
		ACSInformation acs = new ACSInformation();
		ret = acs.ACSInformation_CSta(id, sta);
		if (ret == 1) {
			ret_sta = acs.ACSInformation_GetSta(id);
		}
		out.print("{\"sta\": " + ret + ",\"Switch\": " + ret_sta + "}");
	}
%>

