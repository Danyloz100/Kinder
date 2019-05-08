create table user
(
  ID               int auto_increment
    primary key,
  email            varchar(100) not null,
  password         varchar(20)  not null,
  Name             varchar(30)  null,
  date_createted   datetime     not null,
  Date_last_entred datetime     not null,
  constraint user_email_uindex
    unique (email)
);

create table note
(
  ID               int auto_increment
    primary key,
  user_id          int         not null,
  text             longtext    null,
  title            varchar(50) null,
  date_created     datetime    not null,
  date_last_edited datetime    not null,
  constraint note_user_ID_fk
    foreign key (user_id) references user (ID)
      on update cascade on delete cascade
);

INSERT INTO user (ID, email, password, name, date_createted, last_entred)
VALUES (1, 'andruuu5482@gmail.com', '5482', 'Andru', '2019-04-19 16:01:39', '2019-04-19 16:01:42');

INSERT INTO note (ID, user_id, text, title, date_created, date_last_edited)
VALUES (1, 1, 'Hhello ', 'Hello', '2019-04-19 16:04:41', '2019-04-19 16:04:43');
