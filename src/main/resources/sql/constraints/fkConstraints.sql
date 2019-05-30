ALTER TABLE Person ADD CONSTRAINT Person_fk0 FOREIGN KEY (role_name) REFERENCES Roles(role_name);
ALTER TABLE Person ADD CONSTRAINT Person_fk1 FOREIGN KEY (department_id) REFERENCES Container(person_id);
ALTER TABLE Person ADD CONSTRAINT Person_fk2 FOREIGN KEY (curator_id) REFERENCES Person(person_id);
ALTER TABLE Person ADD CONSTRAINT Person_fk3 FOREIGN KEY (group_id) REFERENCES Container(person_id);
/
ALTER TABLE GradesJournal ADD CONSTRAINT GradesJournal_fk0 FOREIGN KEY (disc_id) REFERENCES Disciplines(disc_id);
ALTER TABLE GradesJournal ADD CONSTRAINT GradesJournal_fk1 FOREIGN KEY (person_id) REFERENCES Person(person_id);
ALTER TABLE GradesJournal ADD CONSTRAINT GradesJournal_fk2 FOREIGN KEY (teacher_id) REFERENCES Person(person_id);
/
ALTER TABLE LessonsPlan ADD CONSTRAINT LessonsPlan_fk0 FOREIGN KEY (disc_id) REFERENCES Disciplines(disc_id);
ALTER TABLE LessonsPlan ADD CONSTRAINT LessonsPlan_fk1 FOREIGN KEY (teacher_id) REFERENCES Person(person_id);
ALTER TABLE LessonsPlan ADD CONSTRAINT LessonsPlan_fk2 FOREIGN KEY (group_id) REFERENCES Container(person_id);
/
ALTER TABLE Messages ADD CONSTRAINT Messages_fk0 FOREIGN KEY (receiver_id) REFERENCES Person(person_id);
ALTER TABLE Messages ADD CONSTRAINT Messages_fk1 FOREIGN KEY (sender_id) REFERENCES Person(person_id);
/
ALTER TABLE Container ADD CONSTRAINT Container_fk0 FOREIGN KEY (chief_id) REFERENCES Person(person_id);
ALTER TABLE Container ADD CONSTRAINT Container_fk1 FOREIGN KEY (type_id) REFERENCES BRANCH_TYPES(type_id);
