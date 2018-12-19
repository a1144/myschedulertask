package com.poi.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author wangyupeng
 */
public class JdbcTemplateUtils {

    private static JdbcTemplate JDBC_TEMPLATE;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        //config.setJdbcUrl("jdbc:mysql://10.101.25.142:3306/metabase");
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
        //config.setUsername("root");
        config.setUsername("root");
        //config.setPassword("078c50");
        config.setPassword("root");
        JDBC_TEMPLATE = new JdbcTemplate(new HikariDataSource(config));
    }

    public static JdbcTemplate getJdbcTemplate() {
        return JDBC_TEMPLATE;
    }

    public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        JDBC_TEMPLATE = jdbcTemplate;
    }

}
