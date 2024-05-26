DROP TABLE IF EXISTS bl_comment;
DROP TABLE IF EXISTS bl_post;
DROP TABLE IF EXISTS bl_user;
DROP SEQUENCE IF EXISTS post_id_seq;
DROP SEQUENCE IF EXISTS comment_id_seq;

CREATE SEQUENCE post_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE comment_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE bl_user (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_mail VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    user_about_yourself VARCHAR(10000),
    user_first_last_name VARCHAR(100),
    user_role VARCHAR(50) NOT NULL,
    date_of_register TIMESTAMP NOT NULL
);

CREATE TABLE bl_post (
    post_id BIGINT NOT NULL DEFAULT nextval('post_id_seq') PRIMARY KEY,
    post_title VARCHAR(255) NOT NULL,
    post_text VARCHAR(25000) NOT NULL,
    publicist_name VARCHAR(100) NOT NULL,
    date_of_publish TIMESTAMP NOT NULL
);

CREATE TABLE bl_comment (
    comment_id BIGINT NOT NULL DEFAULT nextval('comment_id_seq') PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id INT NOT NULL,
    comment_title VARCHAR(255) NOT NULL,
    comment_text VARCHAR(10000) NOT NULL,
    publicist_name VARCHAR(255) NOT NULL,
    date_of_publish TIMESTAMP NOT NULL,
    FOREIGN KEY (post_id) REFERENCES bl_post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES bl_user(user_id) ON DELETE CASCADE
);
