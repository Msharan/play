
CREATE TABLE orders(
   userid        varchar(36)    NOT NULL,
   orderid       varchar(36)    NOT NULL,
   status        varchar(25)    NOT NULL,
   PRIMARY KEY(userid, orderid)
);
