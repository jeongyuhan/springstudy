DROP TABLE BOARDREPLY;

CREATE TABLE BOARDREPLY
(
	REPLYNO NUMBER PRIMARY KEY,
	REPLYWRITER VARCHAR2(32) NOT NULL,
	REPLYCONTENT VARCHAR2(1000),
	REPLYPOSTDATE DATE,
	REPLYIP VARCHAR2(100)
);

DROP SEQUENCE BOARDREPLY_SEQp;

CREATE SEQUENCE BOARDREPLY_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;

ALTER TABLE BOARDREPLY ADD FOREIGN KEY (REPLYWRITER) REFERENCES MEMBER(ID);

COMMIT