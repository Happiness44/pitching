package com.hs2j.space.vo;

import com.hs2j.comm.DTO;

public class SpaceVO extends DTO {
	
	private String seq        ;
	private String title      ;
	private String regDate   ;
	private String price      ;
	private String location   ;
	private String category   ;
	private String url        ;
	private String photoUrl  ;
	
	public SpaceVO(){}
	
	public SpaceVO(String title, String price, String location, String category, String url, String photoUrl) {
		super();
		this.title = title;
		this.price = price;
		this.location = location;
		this.category = category;
		this.url = url;
		this.photoUrl = photoUrl;
	}
	
	
	

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "SpaceVO [seq=" + seq + ", title=" + title + ", regDate=" + regDate + ", price=" + price + ", location="
				+ location + ", category=" + category + ", url=" + url + ", photoUrl=" + photoUrl + ", getWorkDiv()="
				+ getWorkDiv() + ", getSearchDiv()=" + getSearchDiv() + ", getSearchWord()=" + getSearchWord()
				+ ", getPageSize()=" + getPageSize() + ", getPageNum()=" + getPageNum() + ", getTotalCnt()="
				+ getTotalCnt() + "]";
	}


	
}
