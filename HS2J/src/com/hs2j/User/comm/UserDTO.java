package com.hs2j.User.comm;

public class UserDTO {
	private String message;   /** 메시지*/
	private String messageDiv;/** 메시지구분*/
	
	 /**글번호**/
	   private int num;      
	   
	   public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDiv() {
		return messageDiv;
	}

	public void setMessageDiv(String messageDiv) {
		this.messageDiv = messageDiv;
	}

	/**PageSize: 10,20,50,100*/
	   private int pageSize =10;
	   /**PageNum: << 1 2 3 ... >> */
	   private int pageNum =1;
	   
	   /**검색구분*/
	   private String searchDiv;
	   
	   /**검색어*/
	   private String searchWord;
	   
	   public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**총 글수**/
	   private int totalCnt;

	   public int getNum() {
	      return num;
	   }

	   public void setNum(int num) {
	      this.num = num;
	   }

	   public int getTotalCnt() {
	      return totalCnt;
	   }

	   public void setTotalCnt(int totalCnt) {
	      this.totalCnt = totalCnt;
	   }
}
