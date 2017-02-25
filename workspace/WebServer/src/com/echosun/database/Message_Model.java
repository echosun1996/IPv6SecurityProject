package com.echosun.database;
/*
 * Message (UID time message) 
 *
 USE IPV6Security;
 CREATE TABLE Message (UID varchar(100),time VARCHAR(100),message VARCHAR(100),status INT);
 */

public class Message_Model {
	private String UID;
	private String time;
	private String message;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
