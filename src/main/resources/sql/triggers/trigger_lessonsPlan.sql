CREATE trigger BI_LESSONSPLAN
  before insert on LESSONSPLAN
  for each row
begin
  select LESSONSPLAN_SEQ.nextval into :NEW.plan_id from dual;
end;
/