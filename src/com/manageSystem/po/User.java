package com.manageSystem.po;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userLogId;
	private String userName;
	private String userPsw;
	private String userSettings;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userLogId, String userName, String userPsw,
			String userSettings) {
		this.userLogId = userLogId;
		this.userName = userName;
		this.userPsw = userPsw;
		this.userSettings = userSettings;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLogId() {
		return this.userLogId;
	}

	public void setUserLogId(String userLogId) {
		this.userLogId = userLogId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPsw() {
		return this.userPsw;
	}

	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}

	public String getUserSettings() {
		return this.userSettings;
	}

	public void setUserSettings(String userSettings) {
		this.userSettings = userSettings;
	}

}