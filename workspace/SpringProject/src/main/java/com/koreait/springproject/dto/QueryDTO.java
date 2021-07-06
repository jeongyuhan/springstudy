package com.koreait.springproject.dto;

public class QueryDTO {
	
	private int beginRecord;
	private int endRecord;
	private String column;
	private String query;
	
	public QueryDTO() {
		super();
	}
	
	public QueryDTO(int beginRecord, int endRecord, String column, String query) {
		super();
		this.beginRecord = beginRecord;
		this.endRecord = endRecord;
		this.column = column;
		this.query = query;
	}
	
	public int getBeginRecord() {
		return beginRecord;
	}
	public void setBeginRecord(int beginRecord) {
		this.beginRecord = beginRecord;
	}
	public int getEndRecord() {
		return endRecord;
	}
	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
	
}
