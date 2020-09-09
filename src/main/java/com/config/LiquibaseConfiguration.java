package com.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {
    @Bean
    public SpringLiquibase userLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        // 用户模块Liquibase文件路径
        liquibase.setChangeLog("classpath:liquibase/server-db.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setContexts("development,production");
        liquibase.setShouldRun(true);
        liquibase.setResourceLoader(new DefaultResourceLoader());
        // 覆盖Liquibase changelog表名
        liquibase.setDatabaseChangeLogTable("server_changelog_table");
        liquibase.setDatabaseChangeLogLockTable("server_changelog_lock_table");
        return liquibase;
    }
}
