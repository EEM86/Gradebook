CREATE trigger L3G3_BI_BRANCH_TYPE
    before insert on L3G3_BRANCH_TYPE
    for each row
begin
    select L3G3_BRANCH_TYPE_SEQ.nextval into :NEW.type_id from dual;
end;
/
