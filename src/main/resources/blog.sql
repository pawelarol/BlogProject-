
drop table if EXISTS bl_comment;
drop table if EXISTS bl_post;
drop table if EXISTS bl_user;

CREATE TABLE bl_user(
 user_id long not null,
 user_name varchar(255) not null,
 user_email varchar (100) not null,
 dateOfRegister timestamp not null
);

create table bl_post(
 post_id long AUTO_INCREMENT PRIMARY KEY,
 post_title varchar(255) not null,
 post_text varchar(10000) not null,
 publicist_name varchar(255) not null,
 dateOfPublish timestamp not null
);

create table bl_comment(
      comment_id int not null,
    comment_id SERIAL primary key,
     comment_text varchar(10000) not null,
     publicist_name varchar(255) not null,
     dateOfPublish timestamp not null,
    FOREIGN KEY (comment_id) REFERENCES bl_post(post_id)
);