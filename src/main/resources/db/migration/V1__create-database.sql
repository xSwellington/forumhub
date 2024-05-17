CREATE TABLE users
(
    id       bigint       not null AUTO_INCREMENT,
    name     varchar(100) not null,
    email    varchar(100) not null,
    password varchar(255) not null,
    created_at DATETIME not null,
    deleted_at DATETIME null default null,
    unique key unique_user_email(email),
    primary key (id)
);

CREATE TABLE topics
(
    id         bigint       not null auto_increment,
    title      varchar(255) not null,
    message    longtext     not null,
    created_at datetime     not null,
    status     varchar(50),
    author_id  bigint       not null,
    course_id  bigint       not null,
    deleted_at DATETIME null default null,
    primary key (id)
);

CREATE TABLE responses
(
    id         bigint   not null auto_increment,
    message    longtext not null,
    created_at datetime not null,
    solution   longtext not null,
    topic_id   bigint   not null,
    user_id    bigint   not null,
    deleted_at DATETIME null default null,
    primary key (id)
);

CREATE TABLE courses
(
    id       bigint not null auto_increment,
    name     text   not null,
    category text   not null,
    created_at datetime not null,
    deleted_at DATETIME null default null,
    primary key (id)
);

alter table topics add constraint fk_topic_author foreign key (author_id) references users(id);
alter table responses add constraint fk_responses_author foreign key (user_id) references users(id);
alter table responses add constraint fk_responses_topic foreign key (topic_id) references topics(id);
alter table topics add constraint fk_topic_choice foreign key (course_id) references courses(id);

