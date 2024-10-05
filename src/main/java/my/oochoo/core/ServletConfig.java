package my.oochoo.core;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import my.oochoo.jdbc.JdbcService;

@WebListener
public class ServletConfig implements ServletContextListener {
    /**
     * Tomcat 구동 시, 리소스 등록
     * @param sce the ServletContextEvent containing the ServletContext that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("jdbcService", new JdbcService());
    }

    /**
     * 애플리케이션 종료시 리소스 해제
     * @param sce the ServletContextEvent containing the ServletContext that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JdbcService jdbcService = (JdbcService) sce.getServletContext().getAttribute("jdbcService");
    }
}
