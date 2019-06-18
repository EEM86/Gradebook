CREATE TABLE L3G3_lessonsPlan (
	id number(19) NOT NULL,
	disc_id number(19) NOT NULL,
	teacher_id number(19) NOT NULL,
	group_id number(19) NOT NULL,
	hours number(19),
	constraint L3G3_LESSONSPLAN_PK PRIMARY KEY (id)
);
