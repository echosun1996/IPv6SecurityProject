package com.echosun.socket;

/*
 * 这是Servlet启动的线程。
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
			BufferedReader br = new BufferedReader(isr);
			String info = br.readLine();
			if (info != null) {
				br.close();
				System.out.println("客户端:" + info + "\r\n");
			}
		} catch (Exception e) {
		}
	}
}