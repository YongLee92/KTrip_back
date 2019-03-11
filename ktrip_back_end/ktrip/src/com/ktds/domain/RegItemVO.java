package com.ktds.domain;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

public class RegItemVO {
	private String title;
	private String num_min;
	private String num_max;
	private String duration;
	private String price;
	private String contents;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNum_min() {
		return num_min;
	}

	public void setNum_min(String num_min) {
		this.num_min = num_min;
	}

	public String getNum_max() {
		return num_max;
	}

	public void setNum_max(String num_max) {
		this.num_max = num_max;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
