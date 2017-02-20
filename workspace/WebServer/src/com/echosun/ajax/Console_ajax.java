package com.echosun.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Console_ajax extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	/*	  response.setContentType("text/html");  
	        PrintWriter out = response.getWriter();  
	        String name=request.getParameter("name");  
	        System.out.println("name="+name);  
	        UserInfo userInfo=new UserInfo();  
	        userInfo.setUserName("admin01");  
	        userInfo.setPwd("adminpwd01");  
	        UserInfo userInfo2=new UserInfo("admin01", "adminpwd02");  
	        List<UserInfo> list=new ArrayList<UserInfo>();  
	        list.add(userInfo);   
	        list.add(userInfo2);  
	        JSONArray ja=JSONArray.fromObject(list);          
	        out.print(ja);  
	        System.out.println("array--->"+ja);   */ 
		//super.doPost(req, resp);
	}

}
