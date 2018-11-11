package com.hs2j.request.vo;

import com.hs2j.comm.DTO;

public class ReqOneViewVO extends DTO {
		
	private String itemTitle           ;
	private String itemRegPerson	   ;
	private String reqCategory		   ;
	private String reqSender           ;
	private String userSpecialism      ;
	private String userImgFile        ;
	private String userWork            ;
	private String reqMessage          ;
	private String reqSeq              ;
	private String reqMoney			;
	
	
	
	public String getReqMoney() {
		return reqMoney;
	}

	public void setReqMoney(String reqMoney) {
		this.reqMoney = reqMoney;
	}

	public ReqOneViewVO(){}
	
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
	public String getUserSpecialism() {
		return userSpecialism;
	}
	public void setUserSpecialism(String userSpecialism) {
		this.userSpecialism = userSpecialism;
	}
	public String getUserImgFile() {
		return userImgFile;
	}
	public void setUserImgFile(String userImgFile) {
		this.userImgFile = userImgFile;
	}
	public String getUserWork() {
		return userWork;
	}
	public void setUserWork(String userWork) {
		this.userWork = userWork;
	}
	public String getReqMessage() {
		return reqMessage;
	}
	public void setReqMessage(String reqMessage) {
		this.reqMessage = reqMessage;
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
		return "ReqOneViewVO [itemTitle=" + itemTitle + ", itemRegPerson=" + itemRegPerson + ", reqCategory="
				+ reqCategory + ", reqSender=" + reqSender + ", userSpecialism=" + userSpecialism + ", userImgFile="
				+ userImgFile + ", userWork=" + userWork + ", reqMessage=" + reqMessage + ", reqSeq=" + reqSeq
				+ ", reqMoney=" + reqMoney + ", getWorkDiv()=" + getWorkDiv() + ", getSearchDiv()=" + getSearchDiv()
				+ ", getSearchWord()=" + getSearchWord() + ", getPageSize()=" + getPageSize() + ", getPageNum()="
				+ getPageNum() + ", getTotalCnt()=" + getTotalCnt() + "]";
	}	
}