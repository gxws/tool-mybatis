package com.gxws.tool.mybatis.test.tb;

import java.util.Date;

public class TestTb {
	private String id;

	private String name;

	private String noid;

	private String stringName;

	private Integer intName;

	private Date timestampName;

	private Date datetimeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoid() {
		return noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public final String getStringName() {
		return stringName;
	}

	public final void setStringName(String stringName) {
		this.stringName = stringName;
	}

	public final Integer getIntName() {
		return intName;
	}

	public final void setIntName(Integer intName) {
		this.intName = intName;
	}

	public final Date getTimestampName() {
		return timestampName;
	}

	public final void setTimestampName(Date timestampName) {
		this.timestampName = timestampName;
	}

	public final Date getDatetimeName() {
		return datetimeName;
	}

	public final void setDatetimeName(Date datetimeName) {
		this.datetimeName = datetimeName;
	}

}