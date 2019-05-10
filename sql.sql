create table user
(
  id                int auto_increment
    primary key,
  email             varchar(50) not null,
  password          varchar(20) not null,
  name              varchar(30) null,
  date_created      datetime    not null,
  date_last_entered datetime    not null,
  constraint user_email_uindex
    unique (email)
);


create table note
(
  id               int auto_increment
    primary key,
  user_id          int         not null,
  text             longtext    null,
  title            varchar(50) null,
  date_created     datetime    not null,
  date_last_edited datetime    not null,
  constraint note_user_id_fk
    foreign key (user_id) references user (id)
      on delete cascade
);


INSERT INTO xxxkeep2019.user (id, email, password, name, date_created, date_last_entered)
VALUES (1, 'igor@lyutak.com', '1111', 'Igor', '2019-04-19 16:00:08', '2019-04-19 16:00:18');

INSERT INTO xxxkeep2019.note (id, user_id, text, title, date_created, date_last_edited)
VALUES (1, 1, 'Test Hello World!', 'Hello', '2019-04-19 16:04:26', '2019-04-19 16:04:31');