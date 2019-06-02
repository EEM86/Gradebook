ALTER TABLE L3G3_Person ADD CONSTRAINT Person_fk0 FOREIGN KEY (role_id) REFERENCES L3G3_Role(role_id);
ALTER TABLE L3G3_Person ADD CONSTRAINT Person_fk1 FOREIGN KEY (department_id) REFERENCES L3G3_Container(id);
ALTER TABLE L3G3_Person ADD CONSTRAINT Person_fk2 FOREIGN KEY (curator_id) REFERENCES L3G3_Person(person_id);
ALTER TABLE L3G3_Person ADD CONSTRAINT Person_fk3 FOREIGN KEY (group_id) REFERENCES L3G3_Container(id);
/
ALTER TABLE L3G3_GradesJournal ADD CONSTRAINT GradesJournal_fk0 FOREIGN KEY (disc_id) REFERENCES L3G3_Discipline(disc_id);
ALTER TABLE L3G3_GradesJournal ADD CONSTRAINT GradesJournal_fk1 FOREIGN KEY (person_id) REFERENCES L3G3_Person(person_id);
ALTER TABLE L3G3_GradesJournal ADD CONSTRAINT GradesJournal_fk2 FOREIGN KEY (teacher_id) REFERENCES L3G3_Person(person_id);
/
ALTER TABLE L3G3_LessonsPlan ADD CONSTRAINT LessonsPlan_fk0 FOREIGN KEY (disc_id) REFERENCES L3G3_Discipline(disc_id);
ALTER TABLE L3G3_LessonsPlan ADD CONSTRAINT LessonsPlan_fk1 FOREIGN KEY (teacher_id) REFERENCES L3G3_Person(person_id);
ALTER TABLE L3G3_LessonsPlan ADD CONSTRAINT LessonsPlan_fk2 FOREIGN KEY (group_id) REFERENCES L3G3_Container(id);
/
ALTER TABLE L3G3_Message ADD CONSTRAINT Message_fk0 FOREIGN KEY (receiver_id) REFERENCES L3G3_Person(person_id);
ALTER TABLE L3G3_Message ADD CONSTRAINT Message_fk1 FOREIGN KEY (sender_id) REFERENCES L3G3_Person(person_id);
/
ALTER TABLE L3G3_Container ADD CONSTRAINT Container_fk0 FOREIGN KEY (chief_id) REFERENCES L3G3_Person(person_id);
ALTER TABLE L3G3_Container ADD CONSTRAINT Container_fk1 FOREIGN KEY (type_id) REFERENCES L3G3_BRANCH_TYPE(type_id);
