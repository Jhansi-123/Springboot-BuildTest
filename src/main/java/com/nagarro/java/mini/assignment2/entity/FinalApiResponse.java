package com.nagarro.java.mini.assignment2.entity;

import java.util.List;

public class FinalApiResponse {
	
	private List<UserResponse> data;
	private PageInfo pageinformation;
	public List<UserResponse> getData() {
		return data;
	}
	public void setData(List<UserResponse> data) {
		this.data = data;
	}
	public PageInfo getPageinformation() {
		return pageinformation;
	}
	public void setPageinformation(PageInfo pageinformation) {
		this.pageinformation = pageinformation;
	}
	public FinalApiResponse(List<UserResponse> data, PageInfo pageinformation) {
		super();
		this.data = data;
		this.pageinformation = pageinformation;
	}
	public FinalApiResponse() {
		super();
	}
	@Override
	public String toString() {
		return "finalApiResponse [data=" + data + ", pageinformation=" + pageinformation + "]";
	}
	
	

}
