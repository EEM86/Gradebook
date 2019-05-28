CREATE trigger BI_DISCIPLINES
  before insert on DISCIPLINES
  for each row
begin
  select DISCIPLINES_SEQ.nextval into :NEW.disc_id from dual;
end;
/