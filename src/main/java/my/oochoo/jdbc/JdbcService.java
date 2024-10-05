package my.oochoo.jdbc;

import my.oochoo.core.DataSourceUtil;

import java.sql.*;
import java.util.logging.Logger;

public class JdbcService {
    private final Logger LOGGER = Logger.getLogger(JdbcService.class.getName());

    public void executeQuery() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // DataSourceUtil을 사용하여 Connection 얻기
            connection = DataSourceUtil.getConnection();

            // Statement 생성 및 쿼리 실행
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM TEST_TABLE");

            // 결과 처리
            while (resultSet.next()) {
                LOGGER.info("ID: " + resultSet.getInt("ID") + ", Name: " + resultSet.getString("NAME"));
            }

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        } finally {
            // 리소스 반납 (역순으로 닫기)
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
