--整合h2数据库 start
create table if not exists USER (
USE_ID int not null primary key auto_increment,
USE_NAME varchar(100),
USE_SEX varchar(1),
USE_AGE NUMBER(3),
USE_ID_NO VARCHAR(18),
USE_PHONE_NUM VARCHAR(11),
USE_EMAIL VARCHAR(100),
CREATE_TIME DATE,
MODIFY_TIME DATE,
USE_STATE VARCHAR(1));
--整合h2数据库 end
--整合MyBatis start
CREATE TABLE if NOT EXISTS BOOK(
BOOK_ID int not null primary key auto_increment,
PAGE_NUM NUMBER(6),
BOOK_NAME VARCHAR(100),
BOOK_TYPE VARCHAR(1),
BOOK_DESC VARCHAR(1000),
BOOK_PRICE NUMBER(8,2),
CREATE_TIME DATE,
MODIFY_TIME DATE
);
--整合MyBatis end
--整合MyBatis-plus start
CREATE TABLE ANIMAL(
ID int not null primary key AUTO_INCREMENT,
NAME VARCHAR,
TYPE VARCHAR(1),
SEX VARCHAR(1),
MASTER VARCHAR,
IS_DEL NUMBER(1) default 0
);
--整合MyBatis-plus end
