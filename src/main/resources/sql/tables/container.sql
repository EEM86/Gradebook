CREATE TABLE L3G3_container (
  person_id number NOT NULL,
	parent_id number,
	name varchar2(50) NOT NULL,
	chief_id number NOT NULL,
	type_id number,
	institution_city varchar2(50),
	institution_address varchar2(50),
	phone varchar2(50),
	constraint CONTAINER_PK PRIMARY KEY (person_id)
);
