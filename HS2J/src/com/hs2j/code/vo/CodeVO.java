package com.hs2j.code.vo;

import com.hs2j.comm.DTO;

public class CodeVO extends DTO {
	
	private String mstId  ;
	private String dtlId  ;
	private String mstNm  ;
	private String dtlNm  ;
	private String seq    ;
	private String pMstId ;
	private String useYn  ;

	public String getMstId() {
		return mstId;
	}
	public void setMstId(String mstId) {
		this.mstId = mstId;
	}
	public String getDtlId() {
		return dtlId;
	}
	public void setDtlId(String dtlId) {
		this.dtlId = dtlId;
	}
	public String getMstNm() {
		return mstNm;
	}
	public void setMstNm(String mstNm) {
		this.mstNm = mstNm;
	}
	public String getDtlNm() {
		return dtlNm;
	}
	public void setDtlNm(String dtlNm) {
		this.dtlNm = dtlNm;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getpMstId() {
		return pMstId;
	}
	public void setpMstId(String pMstId) {
		this.pMstId = pMstId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	
	@Override
	public String toString() {
		return "CodeVO [mstId=" + mstId + ", dtlId=" + dtlId + ", mstNm=" + mstNm + ", dtlNm=" + dtlNm + ", seq=" + seq
				+ ", pMstId=" + pMstId + ", useYn=" + useYn + ", getSearchDiv()=" + getSearchDiv() + "]";
	}
	
	
	

}
