package com.workspace.server.model;

import java.util.List;

public class AjaxResponseBody {
	private String msg;
	private User result;
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public User getResult() {
		return result;
	}
	
	public void setResult(User result) {
		this.result = result;
	}

}
