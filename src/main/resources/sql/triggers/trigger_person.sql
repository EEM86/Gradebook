CREATE trigger BI_PERSON
  before insert on PERSON
  for each row
begin
  select PERSON_SEQ.nextval into :NEW.person_id from dual;
end;
/