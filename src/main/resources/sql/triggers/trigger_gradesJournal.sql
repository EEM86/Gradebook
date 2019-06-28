CREATE trigger L3G3_BI_GRADESJOURNAL
  before insert on L3G3_GRADESJOURNAL
  for each row
begin
  select L3G3_GRADESJOURNAL_SEQ.nextval into :NEW.ID from dual;
end;
