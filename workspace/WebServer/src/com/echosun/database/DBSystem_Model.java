package com.echosun.database;
/*
 * Message (UID time message) 
 *
CREATE TABLE `System`(`key` varchar(100) PRIMARY KEY, `value` VARCHAR(100));
 */
public class DBSystem_Model {
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
	
}
