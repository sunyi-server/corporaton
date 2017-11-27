package com.corporation.corInfo.domain;

public class searchCorInfoListDTO {
	private String name;

	private String sqrt = "corporation_gmt_create";
	private String sqrt_sc = "desc";
	private String check;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSqrt() {
		return sqrt;
	}

	public void setSqrt(String sqrt) {
		this.sqrt = sqrt;
	}

	public String getSqrt_sc() {
		return sqrt_sc;
	}

	public void setSqrt_sc(String sqrt_sc) {
		this.sqrt_sc = sqrt_sc;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "searchCorInfoListDTO [name=" + name + ", sqrt=" + sqrt + ", sqrt_sc=" + sqrt_sc + ", check=" + check
				+ "]";
	}

}
