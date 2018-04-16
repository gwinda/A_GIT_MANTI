package com.workspace.server.controller;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {
	@NotBlank(message = "用户名不能为空")
	String username	;
	@NotBlank(message = "密码不能为空")
	String password	;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String username) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}




}
