
CREATE TABLE users(
   userid        varchar(36)    NOT NULL PRIMARY KEY,
   username      varchar(50)    NOT NULL UNIQUE KEY,
   password      varchar(100)   NOT NULL,
   email        varchar(256)    NOT NULL,
   status        varchar(25)    NOT NULL
);
