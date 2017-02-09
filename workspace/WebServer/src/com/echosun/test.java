package com.echosun;

import org.apache.commons.codec.digest.DigestUtils;

public class test {
	public static void main(String[] args) {
		String str="admin";
		String salt="f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
		//DigestUtils.sha1Hex(str+salt)  database
		//DigestUtils.sha1Hex(DigestUtils.sha1Hex(str+salt)+check)
		System.out.println("database:"+DigestUtils.sha1Hex(str+salt));
		System.out.println("end:"+DigestUtils.sha1Hex(DigestUtils.sha1Hex(str+salt)+"(XpsJUX>3aCjpicAiAQkV2w@yh_,6],"));
	}
}
