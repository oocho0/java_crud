package my.oochoo.web.Const;

public enum Bean {
    JDBC_TEMPLATE("jdbcTemplate"),
    POST_REPOSITORY("postRepository"),
    USER_REPOSITORY("userRepository"),
    REPLY_REPOSITORY("replyRepository"),
    POST_SERVICE("postService"),
    USER_SERVICE("userService"),
    REPLY_SERVICE("replyService");

    private final String name;

    Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
