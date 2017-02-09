package com.echosun.database;

/*
 * database&LoginUser(username password info)
 CREATE DATABASE IPV6Security;
 USE IPV6Security;
 CREATE TABLE LoginUser (username varchar(100) PRIMARY KEY,password VARCHAR(100),info varchar(100));
 GRANT ALL on IPV6Security.* to echosun;
 */




public class Login_Model {

	private String username;
	private String password;
	private String info;

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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
