CREATE TABLE goal
(
  ID             INT(11)                NOT NULL AUTO_INCREMENT,
  DESCRIPTION      varchar(50) default '' NOT NULL,
  TARGET_DATE varchar(50) default '' NOT NULL,
  FINISH_DATE  varchar(50) default '' NOT NULL,
  STATUS        varchar(50) default '' NOT NULL,
  USERID INT(11),
  PRIMARY KEY (ID)
);