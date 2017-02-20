package com.echosun.login;
import java.util.List;

import com.echosun.database.*;

import org.apache.commons.codec.digest.DigestUtils;

public class Login_check {

	static String str = "abc";
	public boolean UserLogin(User u,String check) throws Exception{
	
		//String salt="f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
		//DigestUtils.sha1Hex(str+salt);
		//System.out.println(check);
		//System.out.println(u.getPassword());
		//System.out.println(u.getPassword().equals(DigestUtils.sha1Hex("92f467dd1166f591ac53ce834f02aa6084868119"+check)));
		
		//boolean pwpass=u.getPassword().equals(DigestUtils.sha1Hex("92f467dd1166f591ac53ce834f02aa6084868119"+check));
		//boolean unpass=u.getUsername().equals("admin");
		boolean pwpass=false;
		boolean unpass=false;
		List<Login_Model> resList=null;
		Login login=new Login();
		resList=login.Login_Sel();
		for(Login_Model l:resList)
		{
			if(l.getUsername().equals(u.getUsername()))
			{
				pwpass=true;
				if(DigestUtils.sha1Hex(l.getPassword()+check).equals(u.getPassword()))
				{
					unpass=true;
				}
				break;
			}
		}
		if(pwpass&&unpass)
			return true;
		return false;
	}
}
