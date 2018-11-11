package com.hs2j.request.vo;

import com.hs2j.comm.DTO;

public class ReqListViewVO extends DTO {
	
	private String itemTitle;
	private String itemRegPerson;
	private String reqCategory;
	private String reqSender;
	private String reqDate;
	private String reqSeq;
	
	public ReqListViewVO(){}
	
	
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getReqSender() {
		return reqSender;
	}
	public void setReqSender(String reqSender) {
		this.reqSender = reqSender;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getReqSeq() {
		return reqSeq;
	}
	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}

	



	public String getReqCategory() {
		return reqCategory;
	}



	public void setReqCategory(String reqCategory) {
		this.reqCategory = reqCategory;
	}



	


	public String getItemRegPerson() {
		return itemRegPerson;
	}



	public void setItemRegPerson(String itemRegPerson) {
		this.itemRegPerson = itemRegPerson;
	}



	@Override
	public String toString() {
		return "ReqListViewVO [itemTitle=" + itemTitle + ", itemRegPerson=" + itemRegPerson + ", reqCategory="
				+ reqCategory + ", reqSender=" + reqSender + ", reqDate=" + reqDate + ", reqSeq=" + reqSeq
				+ ", getWorkDiv()=" + getWorkDiv() + ", getSearchDiv()=" + getSearchDiv() + ", getSearchWord()="
				+ getSearchWord() + ", getPageSize()=" + getPageSize() + ", getPageNum()=" + getPageNum() + "]";
	}
}
