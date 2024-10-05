package my.oochoo.jdbc;

/**
 * H2datavase JDBC 상수
 */
public class JdbcConst {
    /** H2 JDBC URL - H2database 파일 경로
     * 메모리 데이터베이스는 jdbc:h2:mem:testdb */
    public static final String JDBC_URL = "jdbc:h2:~/test";
    /** H2database의 사용자 이름 */
    public static final String USERNAME = "sa";
    /** H2database의 비밀번호 */
    public static final String PASSWORD  = "";
    /** H2 JDBC DRIVER */
    public static final String JDBC_DRIVER = "org.h2.Driver";
}
