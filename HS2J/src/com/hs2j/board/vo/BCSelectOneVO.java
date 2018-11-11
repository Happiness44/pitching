package com.hs2j.board.vo;

import com.hs2j.comm.DTO;

public class BCSelectOneVO extends DTO {
	private String bdSeq;
	private String bdTitle;				//제목
	private String cdDtlNm;				//상세분류 이름(ex. 창업지원뉴스 or 스타트업 이슈 / 헬스,뷰티,패션 or IT or 금융 ...)
	private String bdRegPerson;			//작성자
	private String bdRegDate;			//작성일
	private String bdContents;			//내용
	private String fUrl;				//파일
	
	public BCSelectOneVO() { }

	public BCSelectOneVO(String bdSeq, String bdTitle, String cdDtlNm, String bdRegPerson, String bdRegDate,
			String bdContents, String fUrl) {
		super();
		this.bdSeq = bdSeq;
		this.bdTitle = bdTitle;
		this.cdDtlNm = cdDtlNm;
		this.bdRegPerson = bdRegPerson;
		this.bdRegDate = bdRegDate;
		this.bdContents = bdContents;
		this.fUrl = fUrl;
	}

	public String getBdSeq() {
		return bdSeq;
	}

	public void setBdSeq(String bdSeq) {
		this.bdSeq = bdSeq;
	}

	public String getBdTitle() {
		return bdTitle;
	}

	public void setBdTitle(String bdTitle) {
		this.bdTitle = bdTitle;
	}

	public String getCdDtlNm() {
		return cdDtlNm;
	}

	public void setCdDtlNm(String cdDtlNm) {
		this.cdDtlNm = cdDtlNm;
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

	public String getBdContents() {
		return bdContents;
	}

	public void setBdContents(String bdContents) {
		this.bdContents = bdContents;
	}

	public String getfUrl() {
		return fUrl;
	}

	public void setfUrl(String fUrl) {
		this.fUrl = fUrl;
	}

	@Override
	public String toString() {
		return "BCSelectOneVO [bdSeq=" + bdSeq + ", bdTitle=" + bdTitle + ", cdDtlNm=" + cdDtlNm + ", bdRegPerson="
				+ bdRegPerson + ", bdRegDate=" + bdRegDate + ", bdContents=" + bdContents + ", fUrl=" + fUrl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bdContents == null) ? 0 : bdContents.hashCode());
		result = prime * result + ((bdRegDate == null) ? 0 : bdRegDate.hashCode());
		result = prime * result + ((bdRegPerson == null) ? 0 : bdRegPerson.hashCode());
		result = prime * result + ((bdSeq == null) ? 0 : bdSeq.hashCode());
		result = prime * result + ((bdTitle == null) ? 0 : bdTitle.hashCode());
		result = prime * result + ((cdDtlNm == null) ? 0 : cdDtlNm.hashCode());
		result = prime * result + ((fUrl == null) ? 0 : fUrl.hashCode());
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
		BCSelectOneVO other = (BCSelectOneVO) obj;
		if (bdContents == null) {
			if (other.bdContents != null)
				return false;
		} else if (!bdContents.equals(other.bdContents))
			return false;
		if (bdRegDate == null) {
			if (other.bdRegDate != null)
				return false;
		} else if (!bdRegDate.equals(other.bdRegDate))
			return false;
		if (bdRegPerson == null) {
			if (other.bdRegPerson != null)
				return false;
		} else if (!bdRegPerson.equals(other.bdRegPerson))
			return false;
		if (bdSeq == null) {
			if (other.bdSeq != null)
				return false;
		} else if (!bdSeq.equals(other.bdSeq))
			return false;
		if (bdTitle == null) {
			if (other.bdTitle != null)
				return false;
		} else if (!bdTitle.equals(other.bdTitle))
			return false;
		if (cdDtlNm == null) {
			if (other.cdDtlNm != null)
				return false;
		} else if (!cdDtlNm.equals(other.cdDtlNm))
			return false;
		if (fUrl == null) {
			if (other.fUrl != null)
				return false;
		} else if (!fUrl.equals(other.fUrl))
			return false;
		return true;
	}
}