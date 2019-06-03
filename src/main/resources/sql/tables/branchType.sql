CREATE TABLE L3G3_branch_type (
    type_id number(19) NOT NULL,
    type_name VARCHAR(20) UNIQUE,
    constraint BRANCH_TYPE_PK PRIMARY KEY (type_id)
);
