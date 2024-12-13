package ru.itis.contralwork.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.contralwork.dao.GiftRepository;
import ru.itis.contralwork.dao.GiftRepositoryImpl;
import ru.itis.contralwork.mapper.GiftRowMapper;
import ru.itis.contralwork.service.GiftService;
import ru.itis.contralwork.service.GiftServiceImpl;


import java.io.IOException;
import java.util.Properties;


@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new IllegalStateException("Exception while parse properties file", e);
        }
        try {
            Class.forName(properties.getProperty("dataSource.driver-class"));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Exception while load db driver", e);
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("dataSource.url"));
        hikariConfig.setUsername(properties.getProperty("dataSource.user"));
        hikariConfig.setPassword(properties.getProperty("dataSource.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("dataSource.maximumPoolSize")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        GiftRepository giftRepository = new GiftRepositoryImpl(dataSource, new GiftRowMapper());
        GiftService giftService = new GiftServiceImpl(giftRepository);
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("giftService", giftService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        HikariDataSource dataSource = (HikariDataSource) servletContext.getAttribute("dataSource");
        dataSource.close();
    }
}
