# 스키마 생성
create database `woowa-quiz`;

# Quiz
create table quizzes
(
    quiz_id      bigint not null auto_increment,
    active       bit,
    author       varchar(255),
    created_time datetime,
    question     varchar(255),
    quiz_type    varchar(255),
    solution     varchar(255),
    primary key (quiz_id)
) engine = InnoDB;

# Answer
create table answer
(
    answer_id    bigint not null auto_increment,
    answer       varchar(255),
    author       varchar(255),
    created_time datetime,
    quiz_id      bigint,
    primary key (answer_id)
) engine = InnoDB;

# Quiz - Answer FK
alter table answer
    add constraint FK_QUIZ_QUIZ_ID foreign key (quiz_id) references quizzes (quiz_id);