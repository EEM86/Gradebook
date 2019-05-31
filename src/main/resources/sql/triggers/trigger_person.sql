CREATE trigger L3G3_BI_PERSON
  before insert on L3G3_PERSON
  for each row
begin
  select L3G3_PERSON_SEQ.nextval into :NEW.person_id from dual;
end;
/