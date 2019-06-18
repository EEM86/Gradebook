CREATE trigger L3G3_BI_LESSONSPLAN
  before insert on L3G3_LESSONSPLAN
  for each row
begin
  select L3G3_LESSONSPLAN_SEQ.nextval into :NEW.ID from dual;
end;
/