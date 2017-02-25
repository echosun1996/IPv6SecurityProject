package com.echosun.SSE;

import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.List;

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import com.echosun.database.*;
@WebServlet("/SSEMessage")  
public class SSEMessage extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    
    protected void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        response.setContentType("text/event-stream");//设置事件流  
        response.setCharacterEncoding("UTF-8");//设置编码  
                //获取最新时间并返回  
        PrintWriter writer = response.getWriter();  
        List<Message_Model> res=null;
        Message message=new Message();
        try {
			res=message.Message_Sel();			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String string = "<table align='center'><tr><th>UID</th><th>状态</th><th>消息</th><th>时间</th></tr>"; 
        for(Message_Model i : res)
        {
        	string+=("<tr><td>"+i.getUID()+"</td>"+"<td>"+i.getStatus()+"</td>"+"<td>"+i.getMessage()+"</td>"+"<td>"+i.getTime()+"</td></tr>");
        }
        string+="</table>";
        //System.out.println(string);  
        // send SSE  
        writer.write("data: " + string + "\n\n");  
        try {   //设置间隔时间  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  