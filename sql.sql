create schema shop collate utf8_general_ci;

create table user
(
  iduser int auto_increment,
  Email varchar(50) not null,
  Password varchar(20) not null,
  Name varchar(45) null,
  Regestration datetime not null,
  LastEntered datetime not null,
  constraint Email_UNIQUE
    unique (Email),
  constraint iduser_UNIQUE
    unique (iduser)
);

alter table user
  add primary key (iduser);

