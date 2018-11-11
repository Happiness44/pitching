package com.hs2j.item.comm;

import com.hs2j.User.vo.UserVO;

public class ItemDTO{
	/**WorkDiv*/
	private String workDiv;	

	private int num; 

	   private int totalCnt;

	   /** pageSize: 10, 20, ... */
	   private int pageSize = 10;
	   
	   /** pageNum : <<1 2 3 >>*/
	   private int pageNum = 1;
	   
	   /** : id = 10, name = 20 **/
	   private String searchDiv;
	   
	   /**  */
	   private String searchWord;
	   
	   	/***/
	   private int totalPerson;

		/***/
	   private int totalMoney;
	   
	   /***/
	   private int investMoney;


	   public String getWorkDiv() {
		return workDiv;
	}

	public void setWorkDiv(String workDiv) {
		this.workDiv = workDiv;
	}
	
	public int getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(int investMoney) {
		this.investMoney = investMoney;
	}

		public int getTotalPerson() {
			return totalPerson;
		}

		public void setTotalPerson(int totalPerson) {
			this.totalPerson = totalPerson;
		}

		public int getTotalMoney() {
			return totalMoney;
		}

		public void setTotalMoney(int totalMoney) {
			this.totalMoney = totalMoney;
		}
	   
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
