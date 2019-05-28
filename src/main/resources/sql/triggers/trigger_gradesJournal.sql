CREATE trigger BI_GRADESJOURNAL
  before insert on GRADESJOURNAL
  for each row
begin
  select GRADESJOURNAL_SEQ.nextval into :NEW.journal_id from dual;
end;
/