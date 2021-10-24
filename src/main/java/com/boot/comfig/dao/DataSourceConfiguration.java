package com.boot.comfig.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * Created by eleven on 2018/12/4.
 */

@Configuration
//配置mybatis mapper的扫描路径
@MapperScan("com.boot.dao")
public class DataSourceConfiguration {

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.username}")
    private String jdbcUser;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name="dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUser);
        dataSource.setPassword(jdbcPassword);
        //关闭连接时不自动提交
        dataSource.setAutoCommitOnClose(false);
        dataSource.setMaxStatements(100);
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setAutomaticTestTable("S_C3P0_TEST");
        dataSource.setMaxIdleTime(25000);
        dataSource.setIdleConnectionTestPeriod(18000);
        return dataSource;
    }

}
