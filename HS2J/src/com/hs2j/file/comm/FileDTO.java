package com.hs2j.file.comm;

public class FileDTO {
	/** 글번호	 */
	private int num; 

	/** 총글수	 */
	private int totalCnt;

	/** pageSize: 10, 20, ... */
	private int pageSize = 10;
	
	/** pageNum : <<1 2 3 >>*/
	private int pageNum = 1;
	
	/** 검색 조건 : msg_sender = 10, msg_receiver = 20 */
	private String searchDiv;
	
	/** 검색어 */
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