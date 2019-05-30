CREATE trigger BI_CONTAINER
  before insert on CONTAINER
  for each row
begin
  select CONTAINER_SEQ.nextval into :NEW.person_id from dual;
end;
/