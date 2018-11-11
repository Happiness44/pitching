package com.hs2j.request.vo;

import com.hs2j.comm.DTO;

public class RequestVO extends DTO {
	
	private String seq         ;
	private String item        ;
	private String category    ;
	private String reqMessage     ;
	private String reqDate    ;
	private String sender      ;
	private String money       ;
	private String acceptance  ;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getReq_date() {
		return reqDate;
	}
	public void setReq_date(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getAcceptance() {
		return acceptance;
	}
	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}
	

	

	
	
	
	public RequestVO(String item, String category, String reqMessage, String sender,
			String money, String acceptance) {
		super();
		this.item = item;
		this.category = category;
		this.reqMessage = reqMessage;
		this.sender = sender;
		this.money = money;
		this.acceptance = acceptance;
	}
	
	
	
	public String getReqMessage() {
		return reqMessage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptance == null) ? 0 : acceptance.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((reqMessage == null) ? 0 : reqMessage.hashCode());
		result = prime * result + ((reqDate == null) ? 0 : reqDate.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestVO other = (RequestVO) obj;
		if (acceptance == null) {
			if (other.acceptance != null)
				return false;
		} else if (!acceptance.equals(other.acceptance))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (reqMessage == null) {
			if (other.reqMessage != null)
				return false;
		} else if (!reqMessage.equals(other.reqMessage))
			return false;
		if (reqDate == null) {
			if (other.reqDate != null)
				return false;
		} else if (!reqDate.equals(other.reqDate))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	public void setReqMessage(String reqMessage) {
		this.reqMessage = reqMessage;
	}
	public RequestVO(){}
	@Override
	public String toString() {
		return "RequestVO [seq=" + seq + ", item=" + item + ", category=" + category + ", reqMessage=" + reqMessage
				+ ", reqDate=" + reqDate + ", sender=" + sender + ", money=" + money + ", acceptance=" + acceptance
				+ ", getWorkDiv()=" + getWorkDiv() + ", getSearchDiv()=" + getSearchDiv() + ", getSearchWord()="
				+ getSearchWord() + ", getPageSize()=" + getPageSize() + ", getPageNum()=" + getPageNum()
				+ ", getTotalCnt()=" + getTotalCnt() + "]";
	}
}
