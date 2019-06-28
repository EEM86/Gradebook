CREATE trigger L3G3_BI_ROLE
  before insert on L3G3_ROLE
  for each row
  begin
    select L3G3_ROLE_SEQ.nextval into :NEW.role_id from dual;
  end;
