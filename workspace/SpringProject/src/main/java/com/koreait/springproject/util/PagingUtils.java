package com.koreait.springproject.util;

import com.koreait.springproject.dto.PageDTO;

public class PagingUtils {	
	
	// ajax 사용
	public static PageDTO getPage(int totalRecord, int page) {
		
		int recordPerPage = 5;
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = endRecord < totalRecord ? endRecord : totalRecord;
		
		int totalPage = (totalRecord / recordPerPage) + (totalRecord % recordPerPage > 0 ? 1 : 0);
		int pagePerBlock = 5;
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = beginPage + pagePerBlock - 1;
		endPage = endPage < totalPage ? endPage : totalPage;
		
		PageDTO paging = new PageDTO();
		paging.setTotalRecord(totalRecord);
		paging.setRecordPerPage(recordPerPage);
		paging.setBeginRecord(beginRecord);
		paging.setEndRecord(endRecord);
		paging.setTotalPage(totalPage);
		paging.setPagePerBlock(pagePerBlock);
		paging.setBeginPage(beginPage);
		paging.setEndPage(endPage);
		
		return paging;
	}
	

	
	
}
