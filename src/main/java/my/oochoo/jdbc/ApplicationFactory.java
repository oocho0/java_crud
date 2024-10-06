package my.oochoo.jdbc;

import my.oochoo.web.service.PostService;
import my.oochoo.web.service.PostServiceJdbcImpl;
import my.oochoo.web.service.ReplyService;
import my.oochoo.web.service.ReplyServiceJdbcImpl;

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

    public static PostService getPostService() {
        if(singletonMap.get("postService") == null) {
            singletonMap.put("postService", new PostServiceJdbcImpl(getJdbcTemplate()));
        }
        return (PostService) singletonMap.get("postService");
    }

    public static ReplyService getReplyService() {
        if(singletonMap.get("replyService") == null) {
            singletonMap.put("replyService", new ReplyServiceJdbcImpl(getJdbcTemplate()));
        }
        return (ReplyService) singletonMap.get("replyService");
    }
}
