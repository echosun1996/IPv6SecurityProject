package com.echosun.database;
/*
 * Information (UID name category status) 
 * 
 USE IPV6Security;
 CREATE TABLE `Information` (`UID` varchar(100) PRIMARY KEY,`name` VARCHAR(100),`category` INT,status INT,`address` VARCHAR(20),`check` VARCHAR(20));
 */
public class Hardware_Model {
	private String UID;
	private String name;
	private String address;
	private int category;
	private int status;
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	private String check;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
