DROP TABLE MEMBER;

CREATE TABLE MEMBER
(
	NO NUMBER PRIMARY KEY,
	ID VARCHAR2(32) NOT NULL UNIQUE,
	PW VARCHAR2(32) NOT NULL,
	NAME VARCHAR2(32),
	PHONE VARCHAR2(64),
	EMAIL VARCHAR2(100) NOT NULL UNIQUE,
	ADDRESS VARCHAR2(100),
	REGDATE DATE,
	STATE NUMBER
);

DROP SEQUENCE MEMBER_SEQ;

CREATE SEQUENCE MEMBER_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;

COMMIT