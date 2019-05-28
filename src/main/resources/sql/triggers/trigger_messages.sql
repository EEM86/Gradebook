CREATE trigger BI_MESSAGES
  before insert on MESSAGES
  for each row
begin
  select MESSAGES_SEQ.nextval into :NEW.message_id from dual;
end;
/