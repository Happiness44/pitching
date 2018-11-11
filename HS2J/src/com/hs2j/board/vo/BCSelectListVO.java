package com.hs2j.board.vo;

public class BCSelectListVO {
	private int bdRnum;				//페이징을 위한 번호
	private String bdSeq;			//글 번호
	private String cdDtlNm;			//상세분류 이름
	private String bdTitle;			//제목
	private String bdRegPerson;		//작성자
	private String bdRegDate;		//작성일
	private int bdTotalCnt;			//페이징을 위한 전체 글 수
	
	public BCSelectListVO() { }

	public BCSelectListVO(int bdRnum, String bdSeq, String cdDtlNm, String bdTitle, String bdRegPerson,
			String bdRegDate, int bdTotalCnt) {
		super();
		this.bdRnum = bdRnum;
		this.bdSeq = bdSeq;
		this.cdDtlNm = cdDtlNm;
		this.bdTitle = bdTitle;
		this.bdRegPerson = bdRegPerson;
		this.bdRegDate = bdRegDate;
		this.bdTotalCnt = bdTotalCnt;
	}

	public int getBdRnum() {
		return bdRnum;
	}

	public void setBdRnum(int bdRnum) {
		this.bdRnum = bdRnum;
	}

	public String getBdSeq() {
		return bdSeq;
	}

	public void setBdSeq(String bdSeq) {
		this.bdSeq = bdSeq;
	}

	public String getCdDtlNm() {
		return cdDtlNm;
	}

	public void setCdDtlNm(String cdDtlNm) {
		this.cdDtlNm = cdDtlNm;
	}

	public String getBdTitle() {
		return bdTitle;
	}

	public void setBdTitle(String bdTitle) {
		this.bdTitle = bdTitle;
	}

	public String getBdRegPerson() {
		return bdRegPerson;
	}

	public void setBdRegPerson(String bdRegPerson) {
		this.bdRegPerson = bdRegPerson;
	}

	public String getBdRegDate() {
		return bdRegDate;
	}

	public void setBdRegDate(String bdRegDate) {
		this.bdRegDate = bdRegDate;
	}

	public int getBdTotalCnt() {
		return bdTotalCnt;
	}

	public void setBdTotalCnt(int bdTotalCnt) {
		this.bdTotalCnt = bdTotalCnt;
	}

	@Override
	public String toString() {
		return "BCSelectListVO [bdRnum=" + bdRnum + ", bdSeq=" + bdSeq + ", cdDtlNm=" + cdDtlNm + ", bdTitle=" + bdTitle
				+ ", bdRegPerson=" + bdRegPerson + ", bdRegDate=" + bdRegDate + ", bdTotalCnt=" + bdTotalCnt + "]";
	}
}
