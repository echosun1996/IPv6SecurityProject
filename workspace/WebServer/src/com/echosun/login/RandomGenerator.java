package com.echosun.login;

import java.util.Random;

public class RandomGenerator {
	static String dict = "!#$%()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_abcdefghijklmnopqrstuvwxyz{|}~";
	private String randomString;

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	public void RandomString() {
		String result;
		do {
			result = "";
			while (result.length() <= 30) {
				Random ra = new Random();
				int i = (int) (ra.nextInt(89));// >=0&&<89
				result += dict.charAt(i);
			}
		} while (!result.matches(".*(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).*"));

		randomString = result;
	}

	public void RandomCheckPasswd() {
		String result;

		do {
			int i = 1;
			result = "";
			while (i <= 5) {
				char c = (char) ('A' + Math.random() * ('z' - 'A' + 1));
				result += c;
				i++;
			}
		} while (!result.matches("^[A-Za-z]+$"));

		randomString = result;
	}

	public String getRandomString() {
		return randomString;
	}

}