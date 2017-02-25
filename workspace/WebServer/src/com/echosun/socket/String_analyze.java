package com.echosun.socket;

import com.echosun.database.Hardware;
import com.echosun.database.Hardware_Model;
import com.echosun.database.Message;

public class String_analyze {
	private String cmd;
	private String returnString = null;
	private int back;// 1 hava
	static final int ok = 1;
	static final int error = 0;
	private String ip;
	private int category;

	public String_analyze(String ip, String input) {
		this.ip = ip;
		cmd = input;
		back = 0;
	}

	// return
	public String getReturnString() {
		if (back == 1)
			return returnString;
		else
			return null;
	}

	//
	private String hard_init(String category_tem) throws Exception {
		Hardware test = new Hardware();
		String uid = test.Hardware_GetUID(category_tem) + "";
		String check = "ksbb0";// 5

		Hardware_Model in = new Hardware_Model();
		in.setUID(uid);
		in.setName(uid);
		in.setCategory(category);
		in.setStatus(1);
		in.setAddress(ip);
		in.setCheck(check);

		Hardware hardware = new Hardware();
		hardware.Hardware_Init(in);
		return uid + "#" + check;
	}

	// 主函数
	public int hard_mainfun() throws Exception {
		// System.out.println(cmd);
		String[] resStringx = cmd.split("#");

		// 分段
		if (resStringx.length != 5) {
			System.out.println("split error!");
			return error;
		}

		// 回传确认
		if (!("0".equals(resStringx[0]) || "1".equals(resStringx[0]))) {
			System.out.println("back error!");
			return error;
		}

		// uid确认
		if (resStringx[1].length() != 4) {
			System.out.println("uid length error!");
			return error;
		}

		back = resStringx[0].charAt(0) - '0';
		int uid = Integer.parseInt(resStringx[1]);
		category = uid / 1000;
		int status = Integer.parseInt(resStringx[2]);
		String msg = resStringx[3];
		String check = resStringx[4];

		// System.out.println("back:" + back);
		// System.out.println("uid:" + uid);
		// System.out.println("category:" + category);
		// System.out.println("status:" + status);
		// System.out.println("msg:" + msg);
		// System.out.println("check:" + check);

		Hardware hard_db = new Hardware();
		//初始化
		if (status == 1 && category > 0) {
			returnString = hard_init(category + "");
			return ok;
		}
		//发送消息
		else if (status == 2 || status == 3) {
			if (hard_db.Hardware_check(resStringx[1], check)) {
				Message ms_db = new Message();
				ms_db.Message_Init(uid + "", msg, status);

				Hardware hardware = new Hardware();
				if (status == 2 && !hardware.Hardware_GetSta(uid + "").equals("3"))
					hardware.Hardware_CSta(uid + "", status + "");
				if (status == 3)
					hardware.Hardware_CSta(uid + "", status + "");
				if (back == 1)
					returnString = "update";
				return ok;
			} else {
				if (back == 1)
					returnString = "error";
				return error;
			}
		}

		return ok;

	}

	public static void main(String[] args) throws Exception {
		// back#uid#status#message#check

		// 申请uid 发送1#0000#1##0返回uid#密码
		String_analyze demo1 = new String_analyze("10.30.19.1", "1#1000#1##0");
		int sta1 = demo1.hard_mainfun();
		String ret1 = demo1.getReturnString();
		System.out.println(sta1);
		System.out.println(ret1);
		Thread.sleep(1000);
		System.out.println("###########################");

		// 正常消息 sta=2 ； 报警信息 sta=3
		String_analyze demo2 = new String_analyze("10.30.19.1", "0#1001#2#1normal#ksbb0");
		int sta2 = demo2.hard_mainfun();
		String ret2 = demo2.getReturnString();
		System.out.println(sta2);
		System.out.println(ret2);
		Thread.sleep(1000);
		String_analyze demo3 = new String_analyze("10.30.19.1", "1#1001#3#1workerror#ksbb0");
		int sta3 = demo3.hard_mainfun();
		String ret3 = demo3.getReturnString();
		System.out.println(sta3);
		System.out.println(ret3);
	}

}
