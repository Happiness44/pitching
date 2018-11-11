package com.hs2j.item.vo;

import com.hs2j.comm.DTO;

public class ItemVO extends DTO{
	private String item_seq;
	private String item_title;
	private String item_category;
	private String item_region;
	private String item_start_date;		
	private String item_end_date;	
	private String item_count;
	private String item_branch;
	private String item_contents;		
	private String item_investment;	
	private String item_royalty;	
	private String item_repaydate;
	private String item_file;
	private String item_reg_person;
	private String item_reg_date;	
	private String item_compyn;
	
	
	public String getItem_seq() {
		return item_seq;
	}
	public void setItem_seq(String item_seq) {
		this.item_seq = item_seq;
	}
	public String getItem_title() {
		return item_title;
	}
	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getItem_region() {
		return item_region;
	}
	public void setItem_region(String item_region) {
		this.item_region = item_region;
	}
	public String getItem_start_date() {
		return item_start_date;
	}
	public void setItem_start_date(String item_start_date) {
		this.item_start_date = item_start_date;
	}
	public String getItem_end_date() {
		return item_end_date;
	}
	public void setItem_end_date(String item_end_date) {
		this.item_end_date = item_end_date;
	}
	public String getItem_count() {
		return item_count;
	}
	public void setItem_count(String item_count) {
		this.item_count = item_count;
	}
	public String getItem_branch() {
		return item_branch;
	}
	public void setItem_branch(String item_branch) {
		this.item_branch = item_branch;
	}
	public String getItem_contents() {
		return item_contents;
	}
	public void setItem_contents(String item_contents) {
		this.item_contents = item_contents;
	}
	public String getItem_investment() {
		return item_investment;
	}
	public void setItem_investment(String item_investment) {
		this.item_investment = item_investment;
	}
	public String getItem_royalty() {
		return item_royalty;
	}
	public void setItem_royalty(String tem_royalty) {
		this.item_royalty = tem_royalty;
	}
	public String getItem_repaydate() {
		return item_repaydate;
	}
	public void setItem_repaydate(String item_repaydate) {
		this.item_repaydate = item_repaydate;
	}
	public String getItem_file() {
		return item_file;
	}
	public void setItem_file(String item_file) {
		this.item_file = item_file;
	}
	public String getItem_reg_person() {
		return item_reg_person;
	}
	public void setItem_reg_person(String item_reg_person) {
		this.item_reg_person = item_reg_person;
	}
	public String getItem_reg_date() {
		return item_reg_date;
	}
	public void setItem_reg_date(String item_reg_date) {
		this.item_reg_date = item_reg_date;
	}
	public String getItem_compyn() {
		return item_compyn;
	}
	public void setItem_compyn(String item_compyn) {
		this.item_compyn = item_compyn;
	}
	
	public ItemVO() {}
	
	public ItemVO(String item_seq, String item_title, String item_category, String item_region, String item_start_date,
			String item_end_date, String item_count, String item_branch, String item_contents, String item_investment,
			String item_royalty, String item_repaydate, String item_file, String item_reg_person, String item_reg_date,
			String item_compyn) {
		super();
		this.item_seq = item_seq;
		this.item_title = item_title;
		this.item_category = item_category;
		this.item_region = item_region;
		this.item_start_date = item_start_date;
		this.item_end_date = item_end_date;
		this.item_count = item_count;
		this.item_branch = item_branch;
		this.item_contents = item_contents;
		this.item_investment = item_investment;
		this.item_royalty = item_royalty;
		this.item_repaydate = item_repaydate;
		this.item_file = item_file;
		this.item_reg_person = item_reg_person;
		this.item_reg_date = item_reg_date;
		this.item_compyn = item_compyn;
	}
	
}
