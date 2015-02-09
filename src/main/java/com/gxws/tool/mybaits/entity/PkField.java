package com.gxws.tool.mybaits.entity;

/**
 * @author 朱伟亮
 * @create 2015年2月5日下午1:44:04
 *
 */
public class PkField {

	private String dbColumnName;
	private String entityField;

	public PkField() {
		this.dbColumnName = "id";
		this.entityField = this.dbColumnName;
	}

	public final String getDbColumnName() {
		return dbColumnName;
	}

	public final void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public final String getEntityField() {
		return entityField;
	}

	public final void setEntityField(String entityField) {
		this.entityField = entityField;
	}

}
