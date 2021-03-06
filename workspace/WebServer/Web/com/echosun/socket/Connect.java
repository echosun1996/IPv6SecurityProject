package com.echosun.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.echosun.database.DBSystem;

/*
 * 13140端口启动tcp服务的核心。
 */
@SuppressWarnings("serial")
public class Connect extends HttpServlet {
	public void thread_switch(int i) {
		if (i == 1 && sta == 0) {
			thread.start();
			sta = 1;
		} else if (i == 0 && sta == 1) {
			thread.interrupt();
			sta = 0;
		}
	}

	private ServerSocket server;
	static private int sta = 0;
	private Thread thread;

	public void init() throws ServletException {
		DBSystem dbSystem = new DBSystem();
		try {
			dbSystem.System_Upd("Switch", 0 + "");
		} catch (Exception e) {
			System.out.println("Error in init System(Switch)!");
		}

		System.out.println("=================Init Finish=================");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("{\"sta\": 0,\"Switch\": -1}");
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String status = request.getParameter("status");
		if (sta == 0 && status.equals("1")) {
			server = new ServerSocket(13140);
			System.out.println("start");
		} else if (sta == 1 && status.equals("0")) {
			server.close();
			System.out.println("close");
		}
		thread = new Thread() {
			public void run() {
				try {
					Socket socket = null;
					while (true) {
						if (sta == 1) {
							socket = server.accept();
							SocketServer ss = new SocketServer(socket);
							ss.start();
						}
					}
				} catch (IOException ex) {
				}
			}
		};
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		if (status.equals("1")) {
			out.print("{\"sta\": 1,\"Switch\": 1}");
			thread_switch(1);
		} else if (status.equals("0")) {
			out.print("{\"sta\": 1,\"Switch\": 0}");
			thread_switch(0);
		}
		out.close();
	}
}