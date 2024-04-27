DROP TABLE IF EXISTS bl_comment;
DROP TABLE IF EXISTS bl_post;
DROP TABLE IF EXISTS bl_user;

CREATE TABLE bl_user (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_mail VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    user_about_yourself varchar (10000),
    user_first_last_name VARCHAR(100),
    user_role VARCHAR(50) NOT NULL,
    dateOfRegister TIMESTAMP NOT NULL
);

CREATE TABLE bl_post (
    post_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    post_title VARCHAR(255) NOT NULL,
    post_text VARCHAR(10000) NOT NULL,
    publicist_name VARCHAR(255) NOT NULL,
    dateOfPublish TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES bl_user(user_id) ON DELETE CASCADE
);

CREATE TABLE bl_comment (
    comment_id SERIAL PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_text VARCHAR(10000) NOT NULL,
    publicist_name VARCHAR(255) NOT NULL,
    dateOfPublish TIMESTAMP NOT NULL,
    FOREIGN KEY (post_id) REFERENCES bl_post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES bl_user(user_id)  ON DELETE CASCADE
);
