CREATE trigger BI_BRANCH_TYPES
    before insert on BRANCH_TYPES
    for each row
begin
    select BRANCH_TYPES_SEQ.nextval into :NEW.type_id from dual;
end;
/
