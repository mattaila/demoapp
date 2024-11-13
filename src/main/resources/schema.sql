
DROP TABLE IF EXISTS account;

CREATE TABLE ACCOUNT (
    USER_ID VARCHAR(50) PRIMARY KEY,
    USER_ROLE VARCHAR(50) NOT NULL,
    LAST_LOGIN Timestamp,
    CREATED_AT Timestamp NOT NULL default CURRENT_TIMESTAMP,
    CREATE_USER VARCHAR(50),
    UPDATED_AT Timestamp NOT NULL default CURRENT_TIMESTAMP,
    UPDATE_USER VARCHAR(50),
    DELETE_FLG smallint,
    DELETED_AT Timestamp default CURRENT_TIMESTAMP,
    DELETE_USER VARCHAR(50)
);

DROP TABLE IF EXISTS item;

CREATE TABLE ITEM (
    ID serial PRIMARY KEY,
    ITEM_NAME VARCHAR(50) NOT NULL,
    PRICE INT NOT NULL,
    ITEM_DESCRIPTION1 VARCHAR(100),
    ITEM_DESCRIPTION2 VARCHAR(100),
    ITEM_DESCRIPTION3 VARCHAR(100),
    CREATED_AT Timestamp NOT NULL default CURRENT_TIMESTAMP,
    CREATE_USER VARCHAR(50),
    UPDATED_AT Timestamp NOT NULL default CURRENT_TIMESTAMP,
    UPDATE_USER VARCHAR(50),
    DELETE_FLG smallint,
    DELETED_AT Timestamp default CURRENT_TIMESTAMP,
    DELETE_USER VARCHAR(50)
);