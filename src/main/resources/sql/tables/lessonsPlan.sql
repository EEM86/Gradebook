CREATE TABLE LessonsPlan (
	plan_id number NOT NULL,
	disc_id number NOT NULL,
	teacher_id number NOT NULL,
	group_id number NOT NULL,
	hours number,
	constraint LESSONSPLAN_PK PRIMARY KEY (plan_id)
);
