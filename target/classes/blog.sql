DROP TABLE IF EXISTS bl_comment;
DROP TABLE IF EXISTS bl_post;
DROP TABLE IF EXISTS bl_user;
DROP SEQUENCE IF EXISTS post_id_seq;
DROP SEQUENCE IF EXISTS comment_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;

CREATE SEQUENCE post_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE comment_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE bl_user (
    user_id BIGINT NOT NULL DEFAULT nextval('user_id_seq'),
    user_name VARCHAR(255) NOT NULL,
    user_mail VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    user_about_yourself VARCHAR(10000),
    user_first_last_name VARCHAR(100),
    user_role VARCHAR(50) NOT NULL,
    date_of_register TIMESTAMP NOT NULL,

    PRIMARY KEY (user_id)
);

CREATE TABLE bl_post (
    post_id BIGINT NOT NULL DEFAULT nextval('post_id_seq') PRIMARY KEY,
    user_id BIGINT not null,
    post_title VARCHAR(255) NOT NULL,
    post_text VARCHAR(25000) NOT NULL,
    date_of_publish TIMESTAMP NOT NULL,
    foreign key(user_id) REFERENCES bl_user(user_id) on delete cascade
);

CREATE TABLE bl_comment (
    comment_id BIGINT NOT NULL DEFAULT nextval('comment_id_seq'),
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    comment_title VARCHAR(255) NOT NULL,
    comment_text VARCHAR(10000) NOT NULL,
    date_of_publish TIMESTAMP NOT NULL,

    PRIMARY KEY (comment_id),

    FOREIGN KEY (post_id) REFERENCES bl_post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES bl_user(user_id) ON DELETE CASCADE
);
