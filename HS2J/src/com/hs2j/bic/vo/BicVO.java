package com.hs2j.bic.vo;

import com.hs2j.comm.DTO;

public class BicVO extends DTO{
	private String bicSeq;					//센터 번호
	private String bicName;					//센터 이름
	private String bicPhone;				//센터 전화번호
	private String bicAddress;				//지번 주소
	private String bicRoadNm;				//도로명 주소
	private String bicLatitude;				//위도
	private String bicLongitude;			//경도
	
	public BicVO() { }
	
	public BicVO(String bicSeq, String bicName, String bicPhone, String bicAddress, String bicRoadNm,
			String bicLatitude, String bicLongitude) {
		super();
		this.bicSeq = bicSeq;
		this.bicName = bicName;
		this.bicPhone = bicPhone;
		this.bicAddress = bicAddress;
		this.bicRoadNm = bicRoadNm;
		this.bicLatitude = bicLatitude;
		this.bicLongitude = bicLongitude;
	}

	public String getBicSeq() {
		return bicSeq;
	}

	public void setBicSeq(String bicSeq) {
		this.bicSeq = bicSeq;
	}

	public String getBicName() {
		return bicName;
	}

	public void setBicName(String bicName) {
		this.bicName = bicName;
	}

	public String getBicPhone() {
		return bicPhone;
	}

	public void setBicPhone(String bicPhone) {
		this.bicPhone = bicPhone;
	}

	public String getBicAddress() {
		return bicAddress;
	}

	public void setBicAddress(String bicAddress) {
		this.bicAddress = bicAddress;
	}

	public String getBicRoadNm() {
		return bicRoadNm;
	}

	public void setBicRoadNm(String bicRoadNm) {
		this.bicRoadNm = bicRoadNm;
	}

	public String getBicLatitude() {
		return bicLatitude;
	}

	public void setBicLatitude(String bicLatitude) {
		this.bicLatitude = bicLatitude;
	}

	public String getBicLongitude() {
		return bicLongitude;
	}

	public void setBicLongitude(String bicLongitude) {
		this.bicLongitude = bicLongitude;
	}

	@Override
	public String toString() {
		return "BicVO [bicSeq=" + bicSeq + ", bicName=" + bicName + ", bicPhone=" + bicPhone + ", bicAddress="
				+ bicAddress + ", bicRoadNm=" + bicRoadNm + ", bicLatitude=" + bicLatitude + ", bicLongitude="
				+ bicLongitude + "]";
	}
}
