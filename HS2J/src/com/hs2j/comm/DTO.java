package com.hs2j.comm;

import java.util.HashMap;

/**
 * 공통 VO : 모든 VO의 아버지
 * @author SIST01
 *
 */
public class DTO {
	private String messageDiv;
	
	private String message;
	/** workDiv */
	private String workDiv;


	/** 글번호	 */
	private int num; 

	/** 총글수	 */
	private int totalCnt;

	/** pageSize: 10, 20, ... */
	private int pageSize = 10;
	
	/** pageNum : <<1 2 3 >>*/
	private int pageNum = 1;
	
	/** 검색 조건 : id = 10, name = 20 **/
	private String searchDiv;
	
	/**상세구분 [10:헬스,뷰티,패션 / 20:IT / 30:금융 / 40:문화콘텐츠 / 50:물류 / 60:의료 / 70:교육,키즈 / 80:푸드 / 90:기타]**/
	private String dtlSearchDiv;
	
	/** 검색어 */
	private String searchWord;
	
	/***/
   private int totalPerson;
   
   private HashMap<String,String> map;

	public HashMap<String, String> getMap() {
	return map;
}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	public String getMessageDiv() {
		return messageDiv;
	}

	public void setMessageDiv(String messageDiv) {
		this.messageDiv = messageDiv;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public int getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(int investMoney) {
		this.investMoney = investMoney;
	}

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
	
	public String getDtlSearchDiv() {
		return dtlSearchDiv;
	}

	public void setDtlSearchDiv(String dtlSearchDiv) {
		this.dtlSearchDiv = dtlSearchDiv;
	}
	

}
