package com.workspace.server.controller;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

	int uid	;
	int currentpage	;

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}


	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getCurrentpage() {
		return currentpage;
	}




}
