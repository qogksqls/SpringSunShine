drop table sss_auth.user cascade;
drop table sss_auth.`therapist` cascade;
drop table sss_auth.`parent` cascade;
drop table sss_auth.`child` cascade;
drop table sss_service.`card_game` cascade;
drop table sss_service.`object_card` cascade;
drop table sss_service.`feeling_card` cascade;
drop table sss_service.`answer` cascade;
drop table sss_service.`options` cascade;
drop table sss_service.`consult` cascade;
drop table sss_service.`reservation` cascade;
drop table sss_service.`question` cascade;
drop table sss_service.`survery_question` cascade;
drop table sss_service.`survery_response` cascade;
drop table sss_service.`b_expertise_therapist` cascade;
drop table sss_service.`expertise` cascade;
-- ------------------------------------------------------------- TABLE START -----------------------------------------------

-- ***[schema] sss_auth start***

-- 통합 아이디
CREATE TABLE IF NOT exists sss_auth.`user` (
	`user_id`	CHAR(13)	NOT NULL PRIMARY KEY,
	`id`	VARCHAR(20)	NOT NULL,
	`password`	VARCHAR(255)	NOT NULL,
	`withdraw _flag`	INT	NOT NULL	DEFAULT 0
);

-- 치료사 정보
CREATE TABLE IF NOT exists sss_auth.`therapist` (
	`thera_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`name`	VARCHAR(20)	NOT NULL,
	`email`	VARCHAR(50)	NOT NULL,
	`phone`	VARCHAR(20)	NOT NULL,
	`address`	VARCHAR(80)	NOT NULL,
	`profile_url`	VARCHAR(255)	NOT NULL,
	`thera_id`	CHAR(13)	NOT NULL,
	`thera_intro`	TEXT	NOT NULL,
	`approve_flag`	INT	NOT NULL	DEFAULT 0,
    `academic_careers`	VARCHAR(255)	NOT NULL,
	`careers`	VARCHAR(255)	NOT NULL,
	`licences`	VARCHAR(255)	NOT NULL
);

-- 부모 정보
CREATE TABLE IF NOT exists sss_auth.`parent` (
	`parent_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`name`	VARCHAR(20)	NOT NULL,
	`phone`	VARCHAR(20)	NOT NULL,
	`email`	VARCHAR(50)	NOT NULL,
	`address`	VARCHAR(80)	NOT NULL,
	`parent_id`	CHAR(13)	NOT NULL
);

-- 아이 정보
CREATE TABLE IF NOT exists sss_auth.`child` (
	`child_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`name`	VARCHAR(20)	NOT NULL,
	`birth`	DATE	NOT NULL,
	`gender`	INT	NOT NULL,
	`profile_url`	VARCHAR(255)	NULL,
	`child_id`	CHAR(13)	NOT NULL,
	`parent_id`	CHAR(13)	NOT NULL
);

-- *** [schema] sss_auth end **


-- *** [schema] sss_service start ***

-- 카드 게임 결과 정보
CREATE TABLE IF NOT exists sss_service.`card_game` (
	`game_id`	INT auto_increment	NOT NULL PRIMARY KEY,
	`score`	Integer	NULL,
	`total_time`	DATETIME(6)	NULL,
	`child_no`	Integer	NOT NULL,
	`create_time`	DATETIME(6)	NULL
);

-- 사물 카드 정보
CREATE TABLE IF NOT exists sss_service.`object_card` (
	`card_id`	INT auto_increment	NOT NULL PRIMARY KEY,
	`name`	VARCHAR(255)	NOT NULL,
	`image`	VARCHAR(255)	NOT NULL,
	`question`	VARCHAR(255)	NOT NULL
);

-- 감정 카드 정보
CREATE TABLE IF NOT exists sss_service.`feeling_card` (
	`card_id`	INT auto_increment	NOT NULL PRIMARY KEY,
	`name`	VARCHAR(255)	NOT NULL,
	`image`	VARCHAR(255)	NOT NULL,
	`question`	VARCHAR(255)	NOT NULL
);

-- 문진표 질문 정보
CREATE TABLE IF NOT exists sss_service.`question` (
	`question_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`question_id`	INT	NOT NULL,
	`question_context`	VARCHAR(255)	NOT NULL
);

-- 문진표 응답 정보
CREATE TABLE IF NOT exists sss_service.`answer` (
	`answer_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`child_id`	CHAR(13)	NOT NULL,
	`answer`	VARCHAR(30)	NULL,
	`score1`	INT	NOT NULL	DEFAULT 0,
	`score2`	Integer	NOT NULL	DEFAULT 0,
	`score3`	INT	NOT NULL	DEFAULT 0,
	`create_time`	DATETIME(6)	NULL
);

-- 문진표 문항 별 선택지 정보
CREATE TABLE IF NOT exists sss_service.`options` (
	`option_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`option_id`	INT	NOT NULL,
	`option_context`	VARCHAR(255)	NOT NULL,
	`option_score`	INT	NOT NULL,
	`question_id`	INT	NOT NULL
);

-- 상담 방 정보 
CREATE TABLE IF NOT exists sss_service.`consult` (
	`consult_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`started_time`	DATETIME(6)	NOT NULL,
	`closed_time`	DATETIME(6)	NULL,
	`thera_id`	CHAR(13)	NOT NULL,
	`child_id`	CHAR(13)	NOT NULL,
	`parent_id`	CHAR(13)	NOT NULL,
	`memo`	TEXT	NULL,
	`record`	VARCHAR(255)	NULL
);

-- 상담 예약 정보
CREATE TABLE IF NOT exists sss_service.`reservation` (
	`reserv_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`reserv_time`	DATETIME(6)	NOT NULL,
	`thera_id`	CHAR(13)	NOT NULL,
	`child_id`	CHAR(13)	NOT NULL,
	`parent_id`	CHAR(13)	NOT NULL
);

-- 탈퇴 설문 질문 정보
CREATE TABLE IF NOT exists sss_service.`survery_question` (
	`survery_question_id`	INT auto_increment	NOT NULL PRIMARY KEY,
	`question`	VARCHAR(255)	NOT NULL
);

-- 탈퇴 설문 응답 정보
CREATE TABLE IF NOT exists sss_service.`survery_response` (
	`survery_response_id`	INT auto_increment	NOT NULL PRIMARY KEY,
	`score`	INT	NOT NULL	DEFAULT 0,
	`survery_question_id`	INT	NOT NULL,
	`user_id`	CHAR(13)	NOT NULL
);

-- 치료사와 전문성 중계 정보
CREATE TABLE IF NOT exists sss_service.`b_expertise_therapist` (
	`b_expertise_therapist_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`thera_id`	CHAR(13) NOT NULL,
	`expertise_no`	INT NOT NULL,
    foreign key(expertise_no)
    references expertise(expertise_no) on update cascade on delete cascade
);

-- 전문성 정보
CREATE TABLE IF NOT exists sss_service.`expertise` (
	`expertise_no`	INT auto_increment	NOT NULL PRIMARY KEY,
	`is_kind`	VARCHAR(255)	NOT NULL
    
);

-- *** [schema] sss_service end ***

-- ------------------------------------------------------------- TABLE END -----------------------------------------------------------



-- ------------------------------------------------------------- DUMMY DATE START-----------------------------------------------------------

-- 유저 통합 아이디
INSERT  INTO sss_auth.user  
values
('p0SAddT220804', 'parent001', '$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null,  default),
('p3IsNnN220804','parent002','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('p9wj92M220803','parent003','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('pcdHKGY220804','parent004','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('pDj2dJd220805','parent005','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tLQDOys220805','therapist001','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('to46qFg220805','therapist002','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('toqyD0t220805','therapist003','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tQ33VQz220805','therapist004','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tsCvksB220804','therapist005','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tlAN5Qf220805','therapist006','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI220804','therapist007','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2a0804','therapist008','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2b0804','therapist009','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2c0804','therapist010','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2d0804','therapist011','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2e0804','therapist012','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2f0804','therapist013','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2g0804','therapist014','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2h0804','therapist015','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2i0804','therapist016','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2j0804','therapist017','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2k0804','therapist018','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2l0804','therapist019','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tvSV0xI2n0804','therapist020','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, default),
('tWYjC7k2m0804','therapist999','$2a$10$8XFG9jG4dtHwseg/liZFreGGm1GCL4E1paqm3r1UaAxZ1jcHdrf/u',null, 1);


-- 치료사 정보
INSERT INTO sss_auth.therapist(address, email,name, phone, profile_url, thera_intro, thera_id, academic_careers, careers, licences)
values
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist001@gmail.com', '권혜수', '010-1111-1111', '프로필명', '자기소개', 'tLQDOys220805', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist002@gmail.com', '윤광환', '010-1111-1112', '프로필명', '자기소개', 'to46qFg220805', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist003@gmail.com', '추민용', '010-1111-1113', '프로필명', '자기소개', 'toqyD0t220805', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist004@gmail.com', '황경태', '010-1111-1114', '프로필명', '자기소개', 'tQ33VQz220805', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist005@gmail.com', '하병곤', '010-1111-1115', '프로필명', '자기소개', 'tsCvksB220804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist006@gmail.com', '남한결', '010-1111-1116', '프로필명', '자기소개', 'tlAN5Qf220805', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist007@gmail.com', '백현식', '010-1111-1117', '프로필명', '자기소개', 'tvSV0xI220804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist008@gmail.com', '조희아', '010-1111-1118', '프로필명', '자기소개', 'tvSV0xI2a0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist009@gmail.com', '하준호', '010-1111-1119', '프로필명', '자기소개', 'tvSV0xI2b0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist010@gmail.com', '성우희', '010-1111-1110', '프로필명', '자기소개', 'tvSV0xI2c0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist011@gmail.com', '예수혁', '010-1111-1121', '프로필명', '자기소개', 'tvSV0xI2d0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist012@gmail.com', '정종남', '010-1111-1131', '프로필명', '자기소개', 'tvSV0xI2e0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist013@gmail.com', '조영욱', '010-1111-1141', '프로필명', '자기소개', 'tvSV0xI2f0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist014@gmail.com', '예동철', '010-1111-1151', '프로필명', '자기소개', 'tvSV0xI2g0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist015@gmail.com', '예효은', '010-1111-1161', '프로필명', '자기소개', 'tvSV0xI2h0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist016@gmail.com', '설승용', '010-1111-1171', '프로필명', '자기소개', 'tvSV0xI2i0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist017@gmail.com', '송해영', '010-1111-1181', '프로필명', '자기소개', 'tvSV0xI2j0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist018@gmail.com', '하태훈', '010-1111-1191', '프로필명', '자기소개', 'tvSV0xI2k0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist019@gmail.com', '심정현', '010-1111-1101', '프로필명', '자기소개', 'tvSV0xI2l0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist020@gmail.com', '오한결', '010-1111-1011', '프로필명', '자기소개', 'tvSV0xI2n0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] '),
('서울특별시 강남구 역삼동 테헤란로 212', 'therapist999@gmail.com', '권미르', '010-1111-1211', '프로필명', '자기소개', 'tWYjC7k2m0804', '[경희대학교,생체의공학과,2016,2022] [서울대학교,컴퓨터공학과,2017,2022]', '[삼성,대리,2017-2018,프론트앤드] ', '[컴활,한국,2016,첨부파일] ');


-- 부모 정보
insert into sss_auth.parent(name, phone, email, address, parent_id)
values
('추나라빛','010-2222-1111','parent001@gmail.com','서울특별시 강남구 역삼동 테헤란로 212','p0SAddT220804'),
('조소리','010-2222-1112','parent002@gmail.com','서울특별시 강남구 역삼동 테헤란로 212','p3IsNnN220804'),
('남보람','010-2222-1113','parent003@gmail.com','서울특별시 강남구 역삼동 테헤란로 212','p9wj92M220803'),
('탁가람','010-2222-1114','parent004@gmail.com','서울특별시 강남구 역삼동 테헤란로 212','pcdHKGY220804'),
('남궁호','010-2222-1115','parent005@gmail.com','서울특별시 강남구 역삼동 테헤란로 212','pDj2dJd220805');

-- 아이 정보
insert into sss_auth.child(name, birth, gender, profile_url, child_id, parent_id)
values
('신진욱', '2015-07-23', 1, '프로필명', 'cMJwqp1220804', 'p0SAddT220804'),
('정세혁', '2015-07-23', 1, '프로필명', 'cMJwqp2220804', 'p0SAddT220804'),
('안영석', '2015-07-23', 1, '프로필명', 'cMJwqp3220804', 'p3IsNnN220804'),
('이한길', '2015-07-23', 1, '프로필명', 'cMJwqp4220804', 'p3IsNnN220804'),
('양빛가람', '2015-07-23', 1, '프로필명', 'cMJwq53220804', 'p9wj92M220803'),
('문성주', '2015-07-23', 1, '프로필명', 'cMJwqp6220804', 'p9wj92M220803'),
('탁미숙', '2015-07-23', 2, '프로필명', 'cMJwqp7220804', 'p9wj92M220803'),
('하우람', '2015-07-23', 2, '프로필명', 'cMJwqp8220804', 'pcdHKGY220804'),
('정단비', '2015-07-23', 2, '프로필명', 'cMJwqp9220804', 'pcdHKGY220804'),
('노라온', '2015-07-23', 2, '프로필명', 'cMJwq10220804', 'pDj2dJd220805'); 

-- ------------------------------------------------------------- DUMMY DATE END-----------------------------------------------------------