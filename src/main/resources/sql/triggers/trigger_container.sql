CREATE trigger L3G3_BI_CONTAINER
  before insert on L3G3_CONTAINER
  for each row
begin
  select L3G3_CONTAINER_SEQ.nextval into :NEW.person_id from dual;
end;
/