package com.koreait.contact04.dao;

import java.util.List;

import com.koreait.contact04.dto.Contact;

public interface ContactDAO {

	// 1. 전체 연락처 목록 반환
	public List<Contact> selectContactList();
	
	// 2. 신규 연락처 저장
	public int insertContact(Contact contact);
	
	// 3. 번호를 통한 연락처 정보
	public Contact selectContactByNo(long no);
	
	// 4. 연락처 수정하기
	public int updateContact(Contact contact);
	
	// 5. 연락처 삭제
	public int deleteContact(long no);
	
}
