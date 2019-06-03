CREATE TABLE L3G3_person (
	person_id number(19) NOT NULL,
	role_id number(19 NOT NULL,
	first_name varchar2(20) NOT NULL,
	last_name varchar2(20) NOT NULL,
	email varchar2(30) UNIQUE,
	phone varchar2(15) UNIQUE,
	address varchar2(30) NOT NULL,
	birthday DATE NOT NULL,
	department_id number(19),
	curator_id  number(19),
	group_id  number(19),
	login varchar2(20) UNIQUE NOT NULL,
	password varchar2(20) NOT NULL,
	constraint PERSON_PK PRIMARY KEY (person_id)
);
