package my.oochoo.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static my.oochoo.jdbc.JdbcConst.*;

public class DataSourceUtil {
    /** DataSourceUtil 클래스가 로딩될 때 DataSource가 초기화되도록 static으로 선언 */
    private static final BasicDataSource dataSource;

    /** private 생성자를 통해 초기화 블록 수행 */
    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setDriverClassName(JDBC_DRIVER);

        /* 추가적인 커넥션 풀 설정 */
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(2);
    }

    /** 인스턴스화 방지하기 위해 private 생성자 사용 */
    private DataSourceUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /** DataSource로부터 Connection 반환 */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /** DataSource 객체 반환 (필요시 활용) */
    public static DataSource getDataSource() {
        return dataSource;
    }

}
