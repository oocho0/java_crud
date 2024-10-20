package my.oochoo.jdbc;

import my.oochoo.web.Const.Bean;
import my.oochoo.web.repository.*;
import my.oochoo.web.service.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApplicationFactory {
    private static final Map<String, Object> singletonMap = new HashMap<>();

    private static DataSource getDataSource() {
        return DataSourceUtil.getDataSource();
    }

    private static JdbcTemplate getJdbcTemplate() {
       return (JdbcTemplate) getBean(Bean.JDBC_TEMPLATE.getName(), new JdbcTemplate(getDataSource()));
    }

    public static PostRepository getPostRepository() {
        return (PostRepository) getBean(Bean.POST_REPOSITORY.getName(), new PostRepositoryJdbcImpl(getJdbcTemplate()));
    }

    public static ReplyRepository getReplyRepository() {
        return (ReplyRepository) getBean(Bean.REPLY_REPOSITORY.getName(), new ReplyRepositoryJdbcImpl(getJdbcTemplate()));
    }

    public static UserRepository getUserRepository() {
        return (UserRepository) getBean(Bean.USER_REPOSITORY.getName(), new UserRepositoryJdbcImpl(getJdbcTemplate()));
    }

    public static PostService getPostService() {
        return (PostService) getBean(Bean.POST_SERVICE.getName(), new PostServiceJdbcImpl(getPostRepository()));
    }

    public static ReplyService getReplyService() {
        return (ReplyService) getBean(Bean.REPLY_SERVICE.getName(), new ReplyServiceJdbcImpl(getReplyRepository()));
    }

    public static UserService getUserService() {
        return (UserService) getBean(Bean.USER_SERVICE.getName(), new UserServiceJdbcImpl(getUserRepository()));
    }

    private static Object getBean(String beanName, Object impl) {
        Object bean = singletonMap.get(beanName);
        if(Objects.isNull(bean)) {
            singletonMap.put(beanName, impl);
        }
        return singletonMap.get(beanName);
    }
}
