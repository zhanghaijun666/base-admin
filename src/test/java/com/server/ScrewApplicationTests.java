package com.server;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ScrewApplicationTests {

  @Test
  void documentGeneration() {
    // 数据源
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
    hikariConfig.setJdbcUrl(
        "jdbc:mysql://localhost:3306/admindb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("123456");
    // 设置可以获取tables remarks信息
    hikariConfig.addDataSourceProperty("useInformationSchema", "true");
    hikariConfig.setMinimumIdle(2);
    hikariConfig.setMaximumPoolSize(5);
    DataSource dataSource = new HikariDataSource(hikariConfig);
    // 生成配置
    EngineConfig engineConfig =
        EngineConfig.builder()
            // 生成文件路径
            .fileOutputDir("./doc")
            // 打开目录
            .openOutputDir(true)
            // 文件类型( ".html"、".doc"、".md")
            .fileType(EngineFileType.HTML)
            // 生成模板实现
            .produceType(EngineTemplateType.freemarker)
            .build();

    // 忽略表
    List<String> ignoreTableName =
        Arrays.asList("server_changelog_lock_table", "server_changelog_table");
    // 忽略表前缀
    List<String> ignorePrefix = Collections.singletonList("test_");
    // 忽略表后缀
    List<String> ignoreSuffix = Collections.singletonList("_test");
    ProcessConfig processConfig =
        ProcessConfig.builder()
            // 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
            // 根据名称指定表生成
            .designatedTableName(new ArrayList<>())
            // 根据表前缀生成
            .designatedTablePrefix(new ArrayList<>())
            // 根据表后缀生成
            .designatedTableSuffix(new ArrayList<>())
            // 忽略表名
            .ignoreTableName(ignoreTableName)
            // 忽略表前缀
            .ignoreTablePrefix(ignorePrefix)
            // 忽略表后缀
            .ignoreTableSuffix(ignoreSuffix)
            .build();
    // 配置
    Configuration config =
        Configuration.builder()
            // 版本
            .version("1.0.0")
            // 描述
            .description("base-admin数据库设计文档生成")
            // 数据源
            .dataSource(dataSource)
            // 生成配置
            .engineConfig(engineConfig)
            // 生成配置
            .produceConfig(processConfig)
            .build();
    // 执行生成
    new DocumentationExecute(config).execute();
  }
}
