CREATE TABLE L3G3_discipline (
	disc_id number NOT NULL,
	disc_name varchar2(50) UNIQUE NOT NULL,
	constraint DISCIPLINE_PK PRIMARY KEY (disc_id)
);
