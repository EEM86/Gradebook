CREATE TABLE L3G3_gradesJournal (
	id number(19) NOT NULL,
	disc_id number(19) NOT NULL,
	student_id number(19) NOT NULL,
	grade number(19),
	teacher_id number(19) NOT NULL,
	constraint L3G3_GRADESJOURNAL_PK PRIMARY KEY (id)
);
