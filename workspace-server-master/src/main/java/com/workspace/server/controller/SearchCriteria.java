package com.workspace.server.controller;


public class SearchCriteria {

	private int uid;
	private  int currentpage	;
	private String uCID_list;


	public String getUCID_list() {
		return uCID_list;
	}
	public void setUCID_list(String UCID_list) {
		this.uCID_list = UCID_list;
	}

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
