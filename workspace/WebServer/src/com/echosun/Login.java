package com.echosun;

import org.apache.commons.codec.digest.DigestUtils;

public class Login {

	static String str = "abc";
	public boolean UserLogin(User u,String check) {
	
		//String salt="f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
		//DigestUtils.sha1Hex(str+salt);
		//System.out.println(check);
		//System.out.println(u.getPassword());
		//System.out.println(u.getPassword().equals(DigestUtils.sha1Hex("92f467dd1166f591ac53ce834f02aa6084868119"+check)));
		boolean pwpass=u.getPassword().equals(DigestUtils.sha1Hex("92f467dd1166f591ac53ce834f02aa6084868119"+check));
		boolean unpass=u.getUsername().equals("admin");
		if(pwpass&&unpass)
			return true;
		return false;
	}
}
