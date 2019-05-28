CREATE TABLE Disciplines (
	disc_id number NOT NULL,
	disc_name varchar2(50) UNIQUE NOT NULL,
	constraint DISCIPLINES_PK PRIMARY KEY (disc_id)
);
