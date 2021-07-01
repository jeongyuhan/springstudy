package com.koreait.springproject.dto;

public class PageDTO {
	
	
	private int page;
	private int totalRecord;
	private int recordPerPage;
	private int beginRecord;
	private int endRecord;
	private int totalPage;
	private int pagePerBlock;
	private int beginPage;
	private int endPage;

	public PageDTO() {}
	
	public PageDTO(int page, int totalRecord, int recordPerPage, int beginRecord, int endRecord, int totalPage,
			int pagePerBlock, int beginPage, int endPage) {
		super();
		this.page = page;
		this.totalRecord = totalRecord;
		this.recordPerPage = recordPerPage;
		this.beginRecord = beginRecord;
		this.endRecord = endRecord;
		this.totalPage = totalPage;
		this.pagePerBlock = pagePerBlock;
		this.beginPage = beginPage;
		this.endPage = endPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
	
}
