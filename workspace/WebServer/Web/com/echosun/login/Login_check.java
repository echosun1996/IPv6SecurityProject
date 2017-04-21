package com.echosun.login;

/*
 * 登陆校验类
 */
import java.util.List;

import com.echosun.database.*;

import org.apache.commons.codec.digest.DigestUtils;

public class Login_check {

	static String str = "abc";

	public boolean UserLogin(User u, String check) throws Exception {

		boolean pwpass = false;
		boolean unpass = false;
		List<Login_Model> resList = null;
		Login login = new Login();
		resList = login.Login_Sel();
		for (Login_Model l : resList) {
			if (l.getUsername().equals(u.getUsername())) {
				pwpass = true;
				if (DigestUtils.sha1Hex(l.getPassword() + check).equals(u.getPassword())) {
					unpass = true;
				}
				break;
			}
		}
		if (pwpass && unpass)
			return true;
		return false;
	}
}
