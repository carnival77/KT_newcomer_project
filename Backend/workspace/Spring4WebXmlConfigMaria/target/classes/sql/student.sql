CREATE TABLE DEPT
(
	DEPT_ID 	bigint not null auto_increment primary key,
	DEPT_CODE   int(4)  NOT NULL UNIQUE,
	DEPT_NAME   VARCHAR(30) NOT NULL
);

insert into DEPT (dept_code, dept_name) values (10,'�����а�');
insert into DEPT (dept_code, dept_name) values (20,'��ǻ�Ͱ��а�');
insert into DEPT (dept_code, dept_name) values (30,'������а�');
insert into DEPT (dept_code, dept_name) values (40,'������а�');

commit;

CREATE TABLE STUDENT
(
	STU_ID 		   bigint not null auto_increment primary key,
	STU_CODE       int(6) NOT NULL UNIQUE,
	STU_NAME       VARCHAR(100) NOT NULL,
	STU_AGE        int(3) NOT NULL,
	STU_GRADE      VARCHAR(50),
	STU_DAYNIGHT   VARCHAR(50),
	DEPT_CODE      int(4) NOT NULL,
	FOREIGN KEY (DEPT_CODE) REFERENCES DEPT (DEPT_CODE)
);


insert into student(STU_CODE, STU_NAME, STU_AGE, STU_GRADE, STU_DAYNIGHT, DEPT_CODE) values (1002,'ȫ�浿',20,'1�г�','�ְ�',30);
commit;


CREATE TABLE COURSE
(
	COURSE_ID     bigint not null auto_increment primary key,
	COURSE_CODE   int(4) NOT NULL UNIQUE,
	COURSE_NAME   VARCHAR(100),
	COURSE_INSTRUCTOR VARCHAR(100)
);

insert into COURSE(COURSE_CODE, COURSE_NAME, COURSE_INSTRUCTOR) values (1000,'�ڹ����α׷���','���ڹ�');
insert into COURSE(COURSE_CODE, COURSE_NAME, COURSE_INSTRUCTOR) values (2000,'���̽����α׷���','�����̽�');
commit;

CREATE TABLE COURSE_STATUS
(
    STATUS_ID    bigint not null auto_increment primary key,
	STU_CODE     int(6) NOT NULL,
	COURSE_CODE  int(4) NOT NULL,
	COURSE_SCORE int(4) NOT NULL,
	FOREIGN KEY (STU_CODE) REFERENCES STUDENT(STU_CODE),
	FOREIGN KEY (COURSE_CODE) REFERENCES COURSE(COURSE_CODE)
);

insert into COURSE_STATUS(STU_CODE, COURSE_CODE, COURSE_SCORE) values (1002,1000,90);
insert into COURSE_STATUS(STU_CODE, COURSE_CODE, COURSE_SCORE) values (1002,2000,80);
commit;