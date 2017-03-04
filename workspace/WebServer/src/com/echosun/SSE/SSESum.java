package com.echosun.SSE;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import com.echosun.database.*;
@WebServlet("/SSESum")  
public class SSESum extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    
    protected void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        response.setContentType("text/event-stream");//设置事件流  
        response.setCharacterEncoding("UTF-8");//设置编码  
                //获取最新时间并返回  
        PrintWriter writer = response.getWriter();  
       
        Hardware message=new Hardware();
        //System.out.println(string);  
        // send SSE  
        try {
			writer.write("data: " + message.Hardware_Sum() + "\n\n");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        try {   //设置间隔时间  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  