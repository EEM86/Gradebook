CREATE TABLE L3G3_container (
  id number(19) NOT NULL,
	parent_Id number(19),
	name varchar2(50) NOT NULL UNIQUE,
	chief_id number(19) NOT NULL,
	type_id number(19) NOT NULL,
	institution_city varchar2(50),
	institution_address varchar2(50),
	phone varchar2(50),
	constraint L3G3_CONTAINER_PK PRIMARY KEY (id)
);
