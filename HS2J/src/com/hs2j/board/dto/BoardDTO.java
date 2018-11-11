package com.hs2j.board.dto;

public class BoardDTO {
	/**글번호**/
	private int num;		
	
	/**총 글수**/
	private int totalCnt;
	
	/**PageSize : 10, 20, 50, 100 **/
	private int pageSize = 10;

	/**PageSize : << 1 2 3 ... >> **/
	private int pageNum = 1;
		
	/**검색구분 [10:제목 / 20:작성자 / 30:분류]**/
	private String searchDiv;
	
	/**상세구분 [10:헬스,뷰티,패션 / 20:IT / 30:금융 / 40:문화콘텐츠 / 50:물류 / 60:의료 / 70:교육,키즈 / 80:푸드 / 90:기타]**/
	private String dtlSearchDiv;
	
	/**검색어**/
	private String searchWord;

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

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getDtlSearchDiv() {
		return dtlSearchDiv;
	}

	public void setDtlSearchDiv(String dtlSearchDiv) {
		this.dtlSearchDiv = dtlSearchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}
