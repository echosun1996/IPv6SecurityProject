package com.echosun;

public class User {
	private String username;
	private String password;
	private String checksum;
	private String randomString;

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	public String getRandomString() {

		return randomString;

	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
