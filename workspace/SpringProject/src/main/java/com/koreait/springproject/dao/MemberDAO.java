package com.koreait.springproject.dao;

import com.koreait.springproject.dto.MemberDTO;

public interface MemberDAO {
	
	// 1. 로그인
	public MemberDTO login(MemberDTO memberDTO);
	
	// 2. 아이디 중복 체크
	public int idCheck(String id);
	
	// 3. 회원가입
	public int join(MemberDTO memberDTO);
	
	// 4. 회원탈퇴(state를 0으로 수정)
	public int deleteMember(long no);
	
	// 5. 아이디 찾기
	public MemberDTO findId(String email);
	
	// 6. 비밀번호 찾기
	public MemberDTO findPw(MemberDTO memberDTO);
}
