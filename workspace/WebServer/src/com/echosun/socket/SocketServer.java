package com.echosun.socket;

/*
 * 这是Servlet启动的线程。
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class SocketServer extends Thread {
	private Socket socket;

	public SocketServer(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(
					socket.getInputStream());
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			BufferedReader br = new BufferedReader(isr);
			String info = br.readLine();
			if (info != null) {
				System.out.println("socke:"+socket.getInetAddress().toString());
				String_analyze analyze=new String_analyze(socket.getInetAddress().toString(), info);
				int sta = analyze.hard_mainfun();
				if(sta==1&&analyze.getReturnString()!=null)
					pw.println(analyze.getReturnString());
				else if(sta==0)
					pw.println("------");
				pw.flush();
				br.close();
				System.out.println("客户端:" + info + "\r\n");
			}
		} catch (Exception e) {
		}
	}
}