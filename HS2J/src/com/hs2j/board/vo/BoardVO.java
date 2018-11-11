package com.hs2j.board.vo;

import com.hs2j.comm.DTO;

public class BoardVO extends DTO{
	private String bdSeq;					//글 번호 (BD_SEQ.NEXTVAL)
	private String bdTitle;				//제목
	private String bdCategory;				//분류
	private String bdPostCategory;		//글 분류
	private String bdWithinCategory;		//상세 분류
	private String bdContents;				//내용
	private String bdFile;					//첨부 파일
	private String bdRegPerson;			//작성자
	private String bdRegDate;				//작성일 (SYSDATE)

	public BoardVO() { }

	public BoardVO(String bdSeq, String bdTitle, String bdCategory, String bdPostCategory, String bdWithinCategory,
			String bdContents, String bdFile, String bdRegPerson, String bdRegDate) {
		super();
		this.bdSeq = bdSeq;
		this.bdTitle = bdTitle;
		this.bdCategory = bdCategory;
		this.bdPostCategory = bdPostCategory;
		this.bdWithinCategory = bdWithinCategory;
		this.bdContents = bdContents;
		this.bdFile = bdFile;
		this.bdRegPerson = bdRegPerson;
		this.bdRegDate = bdRegDate;
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

	public String getBdCategory() {
		return bdCategory;
	}

	public void setBdCategory(String bdCategory) {
		this.bdCategory = bdCategory;
	}

	public String getBdPostCategory() {
		return bdPostCategory;
	}

	public void setBdPostCategory(String bdPostCategory) {
		this.bdPostCategory = bdPostCategory;
	}

	public String getBdWithinCategory() {
		return bdWithinCategory;
	}

	public void setBdWithinCategory(String bdWithinCategory) {
		this.bdWithinCategory = bdWithinCategory;
	}

	public String getBdContents() {
		return bdContents;
	}

	public void setBdContents(String bdContents) {
		this.bdContents = bdContents;
	}

	public String getBdFile() {
		return bdFile;
	}

	public void setBdFile(String bdFile) {
		this.bdFile = bdFile;
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

	@Override
	public String toString() {
		return "BoardVO [bdSeq=" + bdSeq + ", bdTitle=" + bdTitle + ", bdCategory=" + bdCategory + ", bdPostCategory="
				+ bdPostCategory + ", bdWithinCategory=" + bdWithinCategory + ", bdContents=" + bdContents + ", bdFile="
				+ bdFile + ", bdRegPerson=" + bdRegPerson + ", bdRegDate=" + bdRegDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bdCategory == null) ? 0 : bdCategory.hashCode());
		result = prime * result + ((bdContents == null) ? 0 : bdContents.hashCode());
		result = prime * result + ((bdFile == null) ? 0 : bdFile.hashCode());
		result = prime * result + ((bdPostCategory == null) ? 0 : bdPostCategory.hashCode());
		result = prime * result + ((bdRegDate == null) ? 0 : bdRegDate.hashCode());
		result = prime * result + ((bdRegPerson == null) ? 0 : bdRegPerson.hashCode());
		result = prime * result + ((bdSeq == null) ? 0 : bdSeq.hashCode());
		result = prime * result + ((bdTitle == null) ? 0 : bdTitle.hashCode());
		result = prime * result + ((bdWithinCategory == null) ? 0 : bdWithinCategory.hashCode());
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
		BoardVO other = (BoardVO) obj;
		if (bdCategory == null) {
			if (other.bdCategory != null)
				return false;
		} else if (!bdCategory.equals(other.bdCategory))
			return false;
		if (bdContents == null) {
			if (other.bdContents != null)
				return false;
		} else if (!bdContents.equals(other.bdContents))
			return false;
		if (bdFile == null) {
			if (other.bdFile != null)
				return false;
		} else if (!bdFile.equals(other.bdFile))
			return false;
		if (bdPostCategory == null) {
			if (other.bdPostCategory != null)
				return false;
		} else if (!bdPostCategory.equals(other.bdPostCategory))
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
		if (bdWithinCategory == null) {
			if (other.bdWithinCategory != null)
				return false;
		} else if (!bdWithinCategory.equals(other.bdWithinCategory))
			return false;
		return true;
	}
}