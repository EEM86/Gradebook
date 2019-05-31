CREATE TABLE L3G3_gradesJournal (
	journal_id number NOT NULL,
	disc_id number NOT NULL,
	person_id number NOT NULL,
	grade number,
	teacher_id number NOT NULL,
	constraint GRADESJOURNAL_PK PRIMARY KEY (journal_id)
);
