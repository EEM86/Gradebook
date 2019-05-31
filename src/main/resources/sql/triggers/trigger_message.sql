CREATE trigger L3G3_BI_MESSAGE
  before insert on L3G3_MESSAGE
  for each row
begin
  select L3G3_MESSAGE_SEQ.nextval into :NEW.message_id from dual;
end;
/