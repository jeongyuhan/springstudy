package com.koreait.contact02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.contact02.dto.Contact;

public class ContactDAO {
	
	@Autowired
	private JdbcTemplate template;
	private String sql;
	
	/* 1. 전체 연락처 목록 반환 */
	public List<Contact> selectContactList() {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT";
		return template.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	/* 2. 신규 연락처 저장 */
	public int insertContact(Contact contact) {
		return template.update(new PreparedStatementCreator() {	
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO CONTACT VALUES (CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
				return ps;
			}
		});
		
		
	}

	// 3. 번호를 통한 연락처 정보
	public Contact selectContactByNo(long no) {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<>(Contact.class), no);
	}
	
	// 4. 연락처 수정하기
	public int updateContact(Contact contact) {
		sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, ADDR = ?, EMAIL = ?, NOTE = ? WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
				ps.setLong(6, contact.getNo());
			}
		});
	}
	// 5. 연락처 삭제
	public int deleteContact(long no) {
		sql = "DELETE FROM CONTACT WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, no);
			}
		});
	}
}
