package my.oochoo.jdbc;

import my.oochoo.web.repository.*;
import my.oochoo.web.service.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ApplicationFactory {
    private static Map<String, Object> singletonMap = new HashMap<>();

    private static DataSource getDataSource() {
        return DataSourceUtil.getDataSource();
    }

    private static JdbcTemplate getJdbcTemplate() {
        if(singletonMap.get("jdbcTemplate") == null) {
            singletonMap.put("jdbcTemplate", new JdbcTemplate(getDataSource()));
        }
        return (JdbcTemplate) singletonMap.get("jdbcTemplate");
    }

    public static PostRepository getPostRepository() {
        if(singletonMap.get("postRepository") == null) {
            singletonMap.put("postRepository", new PostRepositoryJdbcImpl(getJdbcTemplate()));
        }
        return (PostRepository) singletonMap.get("postRepository");
    }

    public static ReplyRepository getReplyRepository() {
        if(singletonMap.get("replyRepository") == null) {
            singletonMap.put("replyRepository", new ReplyRepositoryJdbcImpl(getJdbcTemplate()));
        }
        return (ReplyRepository) singletonMap.get("replyRepository");
    }

    public static UserRepository getUserRepository() {
        if(singletonMap.get("userRepository") == null) {
            singletonMap.put("userRepository", new UserRepositoryJdbcImpl(getJdbcTemplate()));
        }
        return (UserRepository) singletonMap.get("userRepository");
    }

    public static PostService getPostService() {
        if(singletonMap.get("postService") == null) {
            singletonMap.put("postService", new PostServiceJdbcImpl(getPostRepository()));
        }
        return (PostService) singletonMap.get("postService");
    }

    public static ReplyService getReplyService() {
        if(singletonMap.get("replyService") == null) {
            singletonMap.put("replyService", new ReplyServiceJdbcImpl(getReplyRepository()));
        }
        return (ReplyService) singletonMap.get("replyService");
    }

    public static UserService getUserService() {
        if(singletonMap.get("userService") == null) {
            singletonMap.put("userService", new UserServiceJdbcImpl(getUserRepository()));
        }
        return (UserService) singletonMap.get("userService");
    }
}
