package my.oochoo.web.repository;

import my.oochoo.jdbc.JdbcTemplate;
import my.oochoo.jdbc.SqlBuilder;
import my.oochoo.web.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class UserRepositoryJdbcImpl implements UserRepository{
    private final Logger LOGGER = Logger.getLogger(UserRepositoryJdbcImpl.class.getName());
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 사용자 가입
     *
     * @param user User : 등록할 사용자 정보
     * @return boolean : 가입 성공하면 true / 아니면 false
     */
    @Override
    public boolean registerUser(User user) {
        Map<String, Object> userMap = user.toMap();
        String sql = new SqlBuilder().setInsertValues("user", userMap)
                .setDuplicate(userMap)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 사용자 정보 수정
     *
     * @param user User : 수정할 사용자 정보
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    @Override
    public boolean modifyUser(User user) {
        int userKey = user.getUserKey();
        String sql = new SqlBuilder().setUpdate("user")
                .setSet(user.toMap())
                .setWhere("user_key", "=", userKey)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 사용자 정보 수정
     *
     * @param column  String : 수정할 사용자 정보 키
     * @param value   String : 수정할 사용자 정보 값
     * @param userKey int : 수정할 사용자 키
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    @Override
    public boolean modifyUser(String column, Object value, int userKey) {
        String sql = new SqlBuilder().setUpdate("user")
                .setSet(column, value)
                .setWhere("user_key", "=", userKey)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }
    /**
     * 사용자 탈퇴
     *
     * @param userKey int : 탈퇴할 사용자 키
     * @return boolean : 탈퇴 성공하면 true / 아니면 false
     */
    @Override
    public boolean deleteUser(int userKey) {
        String sql = new SqlBuilder().setUpdate("user")
                .setSet("use_yn", "N")
                .setWhere("user_key", "=", userKey)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 특정 사용자 정보 조회
     *
     * @param userKey int : 특정 사용자 키
     * @return User : 조회한 사용자 정보
     */
    @Override
    public Optional<User> getUserById(int userKey) {
        String sql = new SqlBuilder().setSelect("*")
                .setFrom("user")
                .setWhere("user_key", "=", userKey)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, this::getUser);
    }

    @Override
    public Optional<User> getUserByUserId(String userId) {
        String sql = new SqlBuilder().setSelect("*")
                .setFrom("user")
                .setWhere("user_id", "=", userId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, this::getUser);
    }

    /**
     * 특정 사용자 비밀번호 조회
     *
     * @param userKey int : 특정 사용자 키
     * @return String : 조회한 사용자 비밀번호
     */
    @Override
    public String getUserPasswordById(int userKey) {
        String sql = new SqlBuilder().setSelect("password")
                .setFrom("user")
                .setWhere("user_key", "=", userKey)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, resultSet -> resultSet.getString("password"));
    }

    private Optional<List<User>> getUserList(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                this.getUser(resultSet).ifPresentOrElse(userList::add, () -> {throw new RuntimeException("User not found");});
            }
            return Optional.of(userList);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<User> getUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setUserKey(resultSet.getInt("user_key"));
            user.setUserId(resultSet.getString("user_id"));
            user.setPassword(resultSet.getString("password"));
            user.setUserName(resultSet.getString("user_name"));
            user.setRegDate(resultSet.getDate("reg_date"));
            user.setModDate(resultSet.getDate("mod_date"));
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
