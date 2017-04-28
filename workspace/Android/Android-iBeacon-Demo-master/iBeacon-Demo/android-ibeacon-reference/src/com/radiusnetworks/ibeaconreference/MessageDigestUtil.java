package com.radiusnetworks.ibeaconreference;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {

	public static String MessageDigest(String temp) {
		String check = "f-5_srr-5_shLy*l6,^yL5_sr|RV}{xuoDDJJJH@4xvx--5_ewuoDDJJJ|Rew/Ls-5_sBH@Fj,u<|R@";
		MessageDigest md = null;
		try {
		md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
		throw new RuntimeException(e);
		} 
		byte[] digest = md.digest((temp+check).getBytes());
		return MessageDigestUtil.bytetoString(digest); 
	}

	public static String bytetoString(byte[] digest) {
		String str = "";
		String tempStr = "";

		for (int i = 0; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				str = str + "0" + tempStr;
			} else {
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}
}
