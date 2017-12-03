select * from tab
purge recyclebin
desc Plan_member
desc Total_bbs
desc Follow
desc Planner
desc Moneybook
desc Reply
desc Accompany
desc Review
desc Ask
desc FAQ
desc Route
desc Report
select *from Plan_member
select *from Total_bbs
select *from Follow
select *from Planner
select *from Moneybook
select *from Reply
select *from Accompany
select *from Review
select *from Ask
select *from FAQ
select *from Route
select *from Report
/* ȸ�� */
CREATE TABLE Plan_member (
	member_idx NUMBER NOT NULL, /* ȸ����ȣ */
	name VARCHAR2(255) NOT NULL, /* �̸� */
	id VARCHAR2(255) NOT NULL, /* ���̵�(email) */
	lev NUMBER NOT NULL, /* ��� */
	joindate DATE NOT NULL, /* ���Գ�¥ */
	reportnum NUMBER NOT NULL, /* �Ű�Ƚ�� */
	bookmark VARCHAR2(255), /* �ϸ�ũ */
	pwd VARCHAR2(255), /* ��й�ȣ */
	profile_img VARCHAR2(255), /* �������̹��� */
	reason VARCHAR2(255) /* ���� */
);

CREATE UNIQUE INDEX PK_Plan_member
	ON Plan_member (
		member_idx ASC
	);

ALTER TABLE Plan_member
	ADD
		CONSTRAINT PK_Plan_member
		PRIMARY KEY (
			member_idx
		);

/* �Խñ� */
CREATE TABLE Total_bbs (
	bbs_idx NUMBER NOT NULL, /* �Խñ۹�ȣ */
	member_idx NUMBER NOT NULL, /* ȸ����ȣ */
	category VARCHAR2(255) NOT NULL, /* ī�װ� */
	status VARCHAR2(255) NOT NULL, /* ���� */
	subject VARCHAR2(255) NOT NULL, /* ���� */
	content VARCHAR2(255) NOT NULL, /* ���� */
	ref NUMBER NOT NULL, /* �׷� */
	sunben NUMBER NOT NULL, /* �׷��� ���� */
	lev NUMBER NOT NULL, /* �׷��� ���� */
	writedate DATE NOT NULL /* �ۼ����� */
);

CREATE UNIQUE INDEX PK_Total_bbs
	ON Total_bbs (
		bbs_idx ASC,
		member_idx ASC
	);

ALTER TABLE Total_bbs
	ADD
		CONSTRAINT PK_Total_bbs
		PRIMARY KEY (
			bbs_idx,
			member_idx
		);

/* �ȷο� */
CREATE TABLE Follow (
	follow_idx NUMBER NOT NULL, /* follow */
	follower_idx NUMBER NOT NULL /* follower */
);

CREATE UNIQUE INDEX PK_Follow
	ON Follow (
		follow_idx ASC,
		follower_idx ASC
	);

ALTER TABLE Follow
	ADD
		CONSTRAINT PK_Follow
		PRIMARY KEY (
			follow_idx,
			follower_idx
		);

/* �÷� �Խñ� */
CREATE TABLE Planner (
	planner_idx NUMBER NOT NULL, /* �÷� �Խñ۹�ȣ */
	member_idx NUMBER NOT NULL, /* �ۼ��� ȸ����ȣ */
	status VARCHAR2(255) NOT NULL, /* ���� */
	thema VARCHAR2(255) NOT NULL, /* �׸� */
	subject VARCHAR2(255) NOT NULL, /* ���� */
	startday DATE NOT NULL, /* �������� */
	day NUMBER NOT NULL, /* �ϼ� */
	readnum NUMBER NOT NULL, /* ��ȸ�� */
	writedate DATE NOT NULL, /* �ۼ����� */
	recommend VARCHAR2(255) NOT NULL, /* ��õ */
	checklist VARCHAR2(255), /* üũ����Ʈ */
	coverimg VARCHAR2(255), /* Ŀ������ */
	plan_story_subject VARCHAR2(255), /* ���༳�� */
	plan_story_content VARCHAR2(255), /* ����󼼼��� */
	schedule VARCHAR2(4000) /* ������ */
);

CREATE UNIQUE INDEX PK_Planner
	ON Planner (
		planner_idx ASC,
		member_idx ASC
	);

ALTER TABLE Planner
	ADD
		CONSTRAINT PK_Planner
		PRIMARY KEY (
			planner_idx,
			member_idx
		);

/* ����� */
CREATE TABLE Moneybook (
	planner_idx NUMBER NOT NULL, /* �÷� �Խñ۹�ȣ */
	member_idx NUMBER NOT NULL, /* �ۼ��� ȸ����ȣ */
	traffic NUMBER, /* ����� */
	lodge NUMBER, /* ���ں� */
	admission NUMBER, /* ����� */
	food NUMBER, /* ���ĺ� */
	shopping NUMBER, /* ���κ� */
	besides NUMBER /* ��Ÿ */
);

CREATE UNIQUE INDEX PK_Moneybook
	ON Moneybook (
		planner_idx ASC,
		member_idx ASC
	);

ALTER TABLE Moneybook
	ADD
		CONSTRAINT PK_Moneybook
		PRIMARY KEY (
			planner_idx,
			member_idx
		);

/* ��� */
CREATE TABLE Reply (
	reply_idx NUMBER NOT NULL, /* ��۹�ȣ */
	member_idx NUMBER NOT NULL, /* ȸ����ȣ */
	bbs_idx NUMBER NOT NULL, /* �Խñ۹�ȣ */
	content VARCHAR2(255) NOT NULL, /* ���� */
	writedate DATE NOT NULL, /* �ۼ����� */
	ref NUMBER NOT NULL, /* �׷� */
	sunben NUMBER NOT NULL, /* �׷��� ���� */
	lev NUMBER NOT NULL /* �׷��� ���� */
);

CREATE UNIQUE INDEX PK_Reply
	ON Reply (
		reply_idx ASC,
		member_idx ASC,
		bbs_idx ASC
	);

ALTER TABLE Reply
	ADD
		CONSTRAINT PK_Reply
		PRIMARY KEY (
			reply_idx,
			member_idx,
			bbs_idx
		);

/* ����_�Խñ� */
CREATE TABLE Accompany (
	bbs_idx NUMBER NOT NULL, /* �Խñ۹�ȣ */
	member_idx NUMBER NOT NULL, /* ȸ����ȣ */
	country VARCHAR2(255) NOT NULL /* ���� */
);

CREATE UNIQUE INDEX PK_Accompany
	ON Accompany (
		bbs_idx ASC,
		member_idx ASC
	);

ALTER TABLE Accompany
	ADD
		CONSTRAINT PK_Accompany
		PRIMARY KEY (
			bbs_idx,
			member_idx
		);

/* �����ı�_�Խñ� */
CREATE TABLE Review (
	bbs_idx NUMBER NOT NULL, /* �Խñ۹�ȣ */
	member_idx NUMBER NOT NULL, /* ȸ����ȣ */
	thema VARCHAR2(255) NOT NULL, /* �׸� */
	readnum NUMBER NOT NULL, /* ��ȸ�� */
	recommend VARCHAR2(255) NOT NULL, /* ��õ */
	coverimg VARCHAR2(255) /* Ŀ������ */
);

CREATE UNIQUE INDEX PK_Review
	ON Review (
		bbs_idx ASC,
		member_idx ASC
	);

ALTER TABLE Review
	ADD
		CONSTRAINT PK_Review
		PRIMARY KEY (
			bbs_idx,
			member_idx
		);

/* 1:1_�Խñ� */
CREATE TABLE Ask (
	bbs_idx NUMBER NOT NULL, /* �Խñ۹�ȣ */
	member_idx NUMBER NOT NULL, /* ȸ����ȣ */
	ask_status VARCHAR2(255) NOT NULL /* ���� */
);

CREATE UNIQUE INDEX PK_Ask
	ON Ask (
		bbs_idx ASC,
		member_idx ASC
	);

ALTER TABLE Ask
	ADD
		CONSTRAINT PK_Ask
		PRIMARY KEY (
			bbs_idx,
			member_idx
		);

/* FAQ_�Խñ� */
CREATE TABLE FAQ (
	faq_idx INTEGER NOT NULL, /* �Խñ۹�ȣ */
	answer VARCHAR2(255) NOT NULL, /* �亯 */
	question VARCHAR2(255) NOT NULL /* ���� */
);

CREATE UNIQUE INDEX PK_FAQ
	ON FAQ (
		faq_idx ASC
	);

ALTER TABLE FAQ
	ADD
		CONSTRAINT PK_FAQ
		PRIMARY KEY (
			faq_idx
		);


/* ��Ʈ_�Խñ� */
CREATE TABLE Route (
	bbs_idx NUMBER NOT NULL, /* �Խñ۹�ȣ */
	member_idx NUMBER NOT NULL, /* ȸ����ȣ */
	thema VARCHAR2(255) NOT NULL, /* �׸� */
	readnum NUMBER NOT NULL, /* ��ȸ�� */
	recommend VARCHAR2(255) NOT NULL, /* ��õ */
	coverimg VARCHAR2(255) /* Ŀ������ */
);

CREATE UNIQUE INDEX PK_Route
	ON Route (
		bbs_idx ASC,
		member_idx ASC
	);

ALTER TABLE Route
	ADD
		CONSTRAINT PK_Route
		PRIMARY KEY (
			bbs_idx,
			member_idx
		);

/* �Ű� */
CREATE TABLE Report (
	bbs_idx NUMBER NOT NULL, /* �Խñ۹�ȣ */
	writer_idx NUMBER NOT NULL, /* �ۼ���ȸ����ȣ */
	user_idx NUMBER NOT NULL, /* �Ű���ȸ����ȣ */
	reason VARCHAR2(255) NOT NULL, /* ���� */
	status VARCHAR2(255) NOT NULL /* ���� */
);

CREATE UNIQUE INDEX PK_Report
	ON Report (
		bbs_idx ASC,
		writer_idx ASC,
		user_idx ASC
	);

ALTER TABLE Report
	ADD
		CONSTRAINT PK_Report
		PRIMARY KEY (
			bbs_idx,
			writer_idx,
			user_idx
		);

ALTER TABLE Total_bbs
	ADD
		CONSTRAINT FK_Plan_member_TO_Total_bbs
		FOREIGN KEY (
			member_idx
		)
		REFERENCES Plan_member (
			member_idx
		);

ALTER TABLE Follow
	ADD
		CONSTRAINT FK_Plan_member_TO_Follow2
		FOREIGN KEY (
			follow_idx
		)
		REFERENCES Plan_member (
			member_idx
		);

ALTER TABLE Follow
	ADD
		CONSTRAINT FK_Plan_member_TO_Follow
		FOREIGN KEY (
			follower_idx
		)
		REFERENCES Plan_member (
			member_idx
		);

ALTER TABLE Planner
	ADD
		CONSTRAINT FK_Plan_member_TO_Planner
		FOREIGN KEY (
			member_idx
		)
		REFERENCES Plan_member (
			member_idx
		);

ALTER TABLE Moneybook
	ADD
		CONSTRAINT FK_Planner_TO_Moneybook
		FOREIGN KEY (
			planner_idx,
			member_idx
		)
		REFERENCES Planner (
			planner_idx,
			member_idx
		);

ALTER TABLE Reply
	ADD
		CONSTRAINT FK_Plan_member_TO_Reply
		FOREIGN KEY (
			member_idx
		)
		REFERENCES Plan_member (
			member_idx
		);

ALTER TABLE Reply
	ADD
		CONSTRAINT FK_Total_bbs_TO_Reply
		FOREIGN KEY (
			bbs_idx,
			member_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			member_idx
		);
		
ALTER TABLE Accompany
	ADD
		CONSTRAINT FK_Total_bbs_TO_Accompany
		FOREIGN KEY (
			bbs_idx,
			member_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			member_idx
		);

ALTER TABLE Review
	ADD
		CONSTRAINT FK_Total_bbs_TO_Review
		FOREIGN KEY (
			bbs_idx,
			member_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			member_idx
		);

ALTER TABLE Ask
	ADD
		CONSTRAINT FK_Total_bbs_TO_Ask
		FOREIGN KEY (
			bbs_idx,
			member_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			member_idx
		);

ALTER TABLE Route
	ADD
		CONSTRAINT FK_Total_bbs_TO_Route
		FOREIGN KEY (
			bbs_idx,
			member_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			member_idx
		);

ALTER TABLE Report
	ADD
		CONSTRAINT FK_Total_bbs_TO_Report
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			member_idx
		);

ALTER TABLE Report
	ADD
		CONSTRAINT FK_Plan_member_TO_Report
		FOREIGN KEY (
			user_idx
		)
		REFERENCES Plan_member (
			member_idx
		);

CREATE SEQUENCE FAQ_faq_idx
minvalue 0 start with 1 

CREATE SEQUENCE Reply_reply_idx
minvalue 0 start with 1 

CREATE SEQUENCE Moneybook_moneybook_idx
minvalue 0 start with 1 

CREATE SEQUENCE Planner_planner_idx
minvalue 0 start with 1 

CREATE SEQUENCE Total_bbs_bbs_idx
minvalue 0 start with 1 

CREATE SEQUENCE Plan_member_member_idx
minvalue 0 start with 1 

