CREATE trigger L3G3_BI_DISCIPLINE
  before insert on L3G3_DISCIPLINE
  for each row
begin
  select L3G3_DISCIPLINE_SEQ.nextval into :NEW.disc_id from dual;
end;
/