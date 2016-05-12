package com.newer.user;

import java.util.Date;

import com.newer.util.Constants;

public class User {

	/**
	 * 类别-系统管理员
	 */
	public static final String TYPE_SYS_MANAGER = "0";
	/**
	 * 类别-业务用户
	 */
	public static final String TYPE_BUS_USER = "1";

	private Integer id;

	private String name;

	private String account;

	private String pwd;

	private String type;

	private Date createDate;

	private Integer createUser;

	private String createUserName;

	private String isValid = Constants.DATA_VALID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}


	public String getSecondName(){
		return "sssssffffffffffff";
	}

}
