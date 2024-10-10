package my.oochoo.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Logger;

public class JdbcTemplate {
    private final Logger LOGGER = Logger.getLogger(JdbcTemplate.class.getName());
    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 조회
     * @param sql String : SQL
     * @param rowMapper RowMapper<T> : ResultSet 사용
     * @retur
     * @param <T>
     */
    public <T> T selectQuery(String sql, RowMapper<T> rowMapper) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // DataSourceUtil을 사용하여 Connection 얻기
            connection = dataSource.getConnection();

            // Statement 생성 및 쿼리 실행
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            LOGGER.info(sql);
            return rowMapper.mapRow(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 리소스 반납 (역순으로 닫기)
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                LOGGER.warning(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public int updateQuery(String sql) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            LOGGER.info(sql);
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                LOGGER.warning(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
