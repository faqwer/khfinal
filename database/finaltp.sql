
select * from tab

drop table review

drop table route

drop table report

drop table reply

drop table plan_member

drop table moneybook

drop table planner

drop table accompany

drop table ask

drop table follow

drop table faq

drop table total_bbs
drop table planner_recommend

drop table plan_member

PURGE RECYCLEBIN

DROP SEQUENCE FAQ_faq_idx

DROP SEQUENCE Planner_planner_idx

DROP SEQUENCE Total_bbs_bbs_idx

DROP SEQUENCE Plan_member_member_idx

commit

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
/* 회원 */
CREATE TABLE Plan_member (
	member_idx NUMBER NOT NULL, /* 회원번호 */
	name VARCHAR2(255) NOT NULL, /* 이름 */
	id VARCHAR2(255) NOT NULL, /* 아이디(email) */
	lev NUMBER NOT NULL, /* 등급 */
	joindate DATE NOT NULL, /* 가입날짜 */
	reportnum NUMBER NOT NULL, /* 신고횟수 */
	bookmark VARCHAR2(255), /* 북마크 */
	pwd VARCHAR2(255), /* 비밀번호 */
	profile_img VARCHAR2(255), /* 프로필이미지 */
	reason VARCHAR2(255) /* 사유 */
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

/* 게시글 */
CREATE TABLE Total_bbs (
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	status VARCHAR2(255) NOT NULL, /* 상태 */
	subject VARCHAR2(255) NOT NULL, /* 제목 */
	writedate DATE NOT NULL, /* 작성일자 */
	category VARCHAR2(255) NOT NULL, /* 카테고리 */
	content VARCHAR2(255) NOT NULL /* 내용 */
);

CREATE UNIQUE INDEX PK_Total_bbs
	ON Total_bbs (
		bbs_idx ASC,
		writer_idx ASC
	);

ALTER TABLE Total_bbs
	ADD
		CONSTRAINT PK_Total_bbs
		PRIMARY KEY (
			bbs_idx,
			writer_idx
		);

/* 팔로우 */
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

/* 플랜 게시글 */
CREATE TABLE Planner (
	planner_idx NUMBER NOT NULL, /* 플랜 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자 회원번호 */
	status VARCHAR2(255) NOT NULL, /* 상태 */
	subject VARCHAR2(255) NOT NULL, /* 제목 */
	writedate DATE NOT NULL, /* 작성일자 */
	thema VARCHAR2(255) NOT NULL, /* 테마 */
	startday DATE NOT NULL, /* 시작일자 */
	day NUMBER NOT NULL, /* 일수 */
	readnum NUMBER NOT NULL, /* 조회수 */
	checklist VARCHAR2(255), /* 체크리스트 */
	coverimg VARCHAR2(255), /* 커버사진 */
	plan_story_subject VARCHAR2(255), /* 여행설명 */
	plan_story_content VARCHAR2(255), /* 여행상세설명 */
	schedule VARCHAR2(4000) /* 스케줄 */
);

CREATE UNIQUE INDEX PK_Planner
	ON Planner (
		planner_idx ASC,
		writer_idx ASC
	);

ALTER TABLE Planner
	ADD
		CONSTRAINT PK_Planner
		PRIMARY KEY (
			planner_idx,
			writer_idx
		);

/* 가계부 */
CREATE TABLE Moneybook (
	planner_idx NUMBER NOT NULL, /* 플랜 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자 회원번호 */
	traffic NUMBER, /* 교통비 */
	lodge NUMBER, /* 숙박비 */
	admission NUMBER, /* 입장료 */
	food NUMBER, /* 음식비 */
	shopping NUMBER, /* 쇼핑비 */
	besides NUMBER /* 기타 */
);

CREATE UNIQUE INDEX PK_Moneybook
	ON Moneybook (
		planner_idx ASC,
		writer_idx ASC
	);

ALTER TABLE Moneybook
	ADD
		CONSTRAINT PK_Moneybook
		PRIMARY KEY (
			planner_idx,
			writer_idx
		);

/* 댓글 */
CREATE TABLE Reply (
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	user_idx NUMBER NOT NULL, /* 유저회원번호 */
	content VARCHAR2(255) NOT NULL, /* 내용 */
	writedate DATE NOT NULL, /* 작성일자 */
	ref NUMBER NOT NULL, /* 그룹 */
	sunbun NUMBER NOT NULL, /* 그룹의 순번 */
	lev NUMBER NOT NULL /* 그룹의 레벨 */
);

CREATE UNIQUE INDEX PK_Reply
	ON Reply (
		writer_idx ASC,
		bbs_idx ASC,
		user_idx ASC
	);

ALTER TABLE Reply
	ADD
		CONSTRAINT PK_Reply
		PRIMARY KEY (
			writer_idx,
			bbs_idx,
			user_idx
		);

/* 동행_게시글 */
CREATE TABLE Accompany (
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	country VARCHAR2(255) NOT NULL /* 나라 */
);

CREATE UNIQUE INDEX PK_Accompany
	ON Accompany (
		bbs_idx ASC,
		writer_idx ASC
	);

ALTER TABLE Accompany
	ADD
		CONSTRAINT PK_Accompany
		PRIMARY KEY (
			bbs_idx,
			writer_idx
		);

/* 여행후기_게시글 */
CREATE TABLE Review (
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	thema VARCHAR2(255) NOT NULL, /* 테마 */
	readnum NUMBER NOT NULL, /* 조회수 */
	coverimg VARCHAR2(255) /* 커버사진 */
);

CREATE UNIQUE INDEX PK_Review
	ON Review (
		bbs_idx ASC,
		writer_idx ASC
	);

ALTER TABLE Review
	ADD
		CONSTRAINT PK_Review
		PRIMARY KEY (
			bbs_idx,
			writer_idx
		);

/* 1:1_게시글 */
CREATE TABLE Ask (
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	ask_status VARCHAR2(255) NOT NULL, /* 상태 */
	ref NUMBER NOT NULL, /* 그룹 */
	sunbun NUMBER NOT NULL, /* 그룹의 순번 */
	lev NUMBER NOT NULL /* 그룹의 레벨 */
);

CREATE UNIQUE INDEX PK_Ask
	ON Ask (
		bbs_idx ASC,
		writer_idx ASC
	);

ALTER TABLE Ask
	ADD
		CONSTRAINT PK_Ask
		PRIMARY KEY (
			bbs_idx,
			writer_idx
		);

/* FAQ_게시글 */
CREATE TABLE FAQ (
	faq_idx INTEGER NOT NULL, /* 게시글번호 */
	answer VARCHAR2(255) NOT NULL, /* 답변 */
	question VARCHAR2(255) NOT NULL /* 질문 */
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

/* 공지 */
CREATE TABLE Notice (
);

/* 루트_게시글 */
CREATE TABLE Route (
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	thema VARCHAR2(255) NOT NULL, /* 테마 */
	readnum NUMBER NOT NULL, /* 조회수 */
	coverimg VARCHAR2(255) /* 커버사진 */
);

CREATE UNIQUE INDEX PK_Route
	ON Route (
		bbs_idx ASC,
		writer_idx ASC
	);

ALTER TABLE Route
	ADD
		CONSTRAINT PK_Route
		PRIMARY KEY (
			bbs_idx,
			writer_idx
		);

/* 신고 */
CREATE TABLE Report (
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	user_idx NUMBER NOT NULL, /* 신고자회원번호 */
	reason VARCHAR2(255) NOT NULL, /* 사유 */
	status VARCHAR2(255) NOT NULL /* 상태 */
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

/* 통합추천 */
CREATE TABLE Total_recommend (
	bbs_idx NUMBER NOT NULL, /* 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자회원번호 */
	user_idx NUMBER NOT NULL /* 유저회원번호 */
);

CREATE UNIQUE INDEX PK_Total_recommend
	ON Total_recommend (
		bbs_idx ASC,
		writer_idx ASC,
		user_idx ASC
	);

ALTER TABLE Total_recommend
	ADD
		CONSTRAINT PK_Total_recommend
		PRIMARY KEY (
			bbs_idx,
			writer_idx,
			user_idx
		);

/* 플랜추천 */
CREATE TABLE Planner_recommend (
	planner_idx NUMBER NOT NULL, /* 플랜 게시글번호 */
	writer_idx NUMBER NOT NULL, /* 작성자 회원번호 */
	user_idx NUMBER NOT NULL /* 유저회원번호 */
);

CREATE UNIQUE INDEX PK_Planner_recommend
	ON Planner_recommend (
		planner_idx ASC,
		writer_idx ASC,
		user_idx ASC
	);

ALTER TABLE Planner_recommend
	ADD
		CONSTRAINT PK_Planner_recommend
		PRIMARY KEY (
			planner_idx,
			writer_idx,
			user_idx
		);

ALTER TABLE Total_bbs
	ADD
		CONSTRAINT FK_Plan_member_TO_Total_bbs
		FOREIGN KEY (
			writer_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Follow
	ADD
		CONSTRAINT FK_Plan_member_TO_Follow2
		FOREIGN KEY (
			follow_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Follow
	ADD
		CONSTRAINT FK_Plan_member_TO_Follow
		FOREIGN KEY (
			follower_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Planner
	ADD
		CONSTRAINT FK_Plan_member_TO_Planner
		FOREIGN KEY (
			writer_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Moneybook
	ADD
		CONSTRAINT FK_Planner_TO_Moneybook
		FOREIGN KEY (
			planner_idx,
			writer_idx
		)
		REFERENCES Planner (
			planner_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Reply
	ADD
		CONSTRAINT FK_Total_bbs_TO_Reply
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Reply
	ADD
		CONSTRAINT FK_Plan_member_TO_Reply
		FOREIGN KEY (
			user_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Accompany
	ADD
		CONSTRAINT FK_Total_bbs_TO_Accompany
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Review
	ADD
		CONSTRAINT FK_Total_bbs_TO_Review
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Ask
	ADD
		CONSTRAINT FK_Total_bbs_TO_Ask
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Route
	ADD
		CONSTRAINT FK_Total_bbs_TO_Route
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Report
	ADD
		CONSTRAINT FK_Total_bbs_TO_Report
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Report
	ADD
		CONSTRAINT FK_Plan_member_TO_Report
		FOREIGN KEY (
			user_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Total_recommend
	ADD
		CONSTRAINT FK_Total_bbs_TO_Total_recommend
		FOREIGN KEY (
			bbs_idx,
			writer_idx
		)
		REFERENCES Total_bbs (
			bbs_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Total_recommend
	ADD
		CONSTRAINT FK_Plan_member_TO_Total_recommend
		FOREIGN KEY (
			user_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Planner_recommend
	ADD
		CONSTRAINT FK_Planner_TO_Planner_recommend
		FOREIGN KEY (
			planner_idx,
			writer_idx
		)
		REFERENCES Planner (
			planner_idx,
			writer_idx
		)
		ON DELETE CASCADE;

ALTER TABLE Planner_recommend
	ADD
		CONSTRAINT FK_Plan_member_TO_Planner_recommend
		FOREIGN KEY (
			user_idx
		)
		REFERENCES Plan_member (
			member_idx
		)
		ON DELETE CASCADE;

CREATE SEQUENCE FAQ_faq_idx
minvalue 0 start with 1 

CREATE SEQUENCE Planner_planner_idx
minvalue 0 start with 1 

CREATE SEQUENCE Total_bbs_bbs_idx
minvalue 0 start with 1 

CREATE SEQUENCE Plan_member_member_idx
minvalue 0 start with 1 

