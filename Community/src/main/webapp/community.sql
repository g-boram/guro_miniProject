# JSP Model1 Project Schema
# D/N : community 

drop database if exists community;

create database if not exists community;
use community;


######### 가입회원 테이블 시작 #########
create table member (
num					int 		unique 		auto_increment,
uid 				char(30) 											,
upw 				char(30) 	not null								,
uname 				char(30) 	not null								,
uemail 				char(100) 	not null								,
gender 				char(2) 											,
ubirthday 			char(10) 											,
uzipcode 			char(8) 											,
uaddr				char(100) 											,
uhobby				char(10) 											,
ujob				char(30) 											,
jointm 				timestamp											,
constraint 	primary key(uid)
);

# drop table member;
desc member;
select * from member order by num desc limit 0, 10;
######### 가입회원 테이블 끝 #########


## ID 중복 체크용 데이터 입력
insert into member (
uid, upw, uname, uemail, gender, ubirthday,
uzipcode, uaddr, uhobby, ujob, jointm
) values (
'tester01', '1234', '테스터01', 'tester01@google.com', 2, 830815,
'120-170', '서울 서대문구 대현동', '01011', '전문직', now()
);

insert into member (
uid, upw, uname, uemail, gender, ubirthday,
uzipcode, uaddr, uhobby, ujob, jointm
) values (
'you', '1234', '지윤한', 'you@naver.com', 1, 920425,
'134-031', '서울 강동구 성내1동 304', '10110', '학생', now()
);

# select num, uid, upw, uname from member order by num desc limit 0, 10;


select * from member order by num desc limit 0, 10;



######### 회원가입용 우편번호 테이블 시작 #########
create table tblzipcode(
zipcode 				char(7),
area1 				char(10),
area2 				char(20),
area3 				char(40),
area4 				char(20)
);

desc tblzipcode;
select * from tblzipcode limit 0, 10;
######### 회원가입용 우편번호 테이블 끝 #########




######### 게시판 테이블 시작 #########
create table tblboard (
    num         int                 auto_increment	,
    uid 		char(30) 			not null		,				
    uname		char(30)			not null		,
    subject     char(50)			not null		,
    content		text				null			,    
    pos			int					not null		,
    ref			int					not null		,
    depth		int					not null		,
    regtm		datetime			not null		,
    ip			char(15)			null			,    
    readcnt		int					not null		,
    filename	char(50)			null			,
    filesize	int					null			,
    constraint	primary key(num)
);
# originfname, uploadfilename
# 원본파일명   서버에 업로드된 파일명

desc tblboard;
select * from tblboard order by num desc limit 0, 10;
######### 게시판 테이블 끝 #########

