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

@WebServlet("/SSEWarning")
public class SSEWarning extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/event-stream");// 设置事件流
		response.setCharacterEncoding("UTF-8");// 设置编码
		// 获取最新时间并返回
		PrintWriter writer = response.getWriter();
		List<Hardware_Model> res = null;
		Hardware message = new Hardware();
		try {
			res = message.Hardware_Warn();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String string = "<table class='table table-condensed'<thead><tr><th>设备编号</th><th>设备备注</th><th>设备类别</th></tr></thead><tbody>";
		for (Hardware_Model i : res) {
			string += ("<tr class='cus-congif-state3'><td>" + i.getUID() + "</td>" + "<td>" + i.getName() + "</td>"+ "<td>" + i.getCategory() + "</td>" + "</tr>");
		}
		string += "</table>";
		// System.out.println(string);
		// send SSE
		writer.write("data: " + string + "\n\n");
		try { // 设置间隔时间
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}