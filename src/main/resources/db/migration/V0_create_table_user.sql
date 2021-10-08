create table user (
  id int primary key AUTO_INCREMENT,
  username varchar(255) not null,
  email varchar(255) not null,
  password varchar(255),
  phone varchar(50),
  enabled smallint default 1,
  status smallint default 1,
  created_ts timestamp default CURRENT_TIMESTAMP,
  modified_ts timestamp default CURRENT_TIMESTAMP,

unique(email)
);