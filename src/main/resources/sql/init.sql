CREATE TABLE post (
    postId INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    reg_date DATETIME NOT NULL,
    mod_date DATETIME NOT NULL,
    use_yn VARCHAR(1) NOT NULL,
    PRIMARY KEY (postId)
);

CREATE TABLE reply (
    replyId INT(11) NOT NULL AUTO_INCREMENT,
    referId INT(11) NOT NULL,
    author VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    reg_date DATETIME NOT NULL,
    mod_date DATETIME NOT NULL,
    use_yn VARCHAR(1) NOT NULL,
    replyLevel INT(11) NOT NULL,
    PRIMARY KEY (replyId)
);

CREATE TABLE user {
    userKey INT(11) NOT NULL AUTO_INCREMENT,
    userId VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    reg_date DATETIME NOT NULL,
    mod_date DATETIME NOT NULL,
    PRIMARY KEY (userKey)
}