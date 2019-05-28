CREATE TABLE branch_types (
    type_id number NOT NULL,
    type_name VARCHAR(20) UNIQUE,
    constraint BRANCH_TYPES_PK PRIMARY KEY (type_id)
);
