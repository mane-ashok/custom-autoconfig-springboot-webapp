package org.ashok.custom.autoconfig;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(before = DataSourceAutoConfiguration.class)
@ConditionalOnClass(JdbcDataSource.class)
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(prefix = "custom.autoconfig", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(CustomAutoConfigDataSourceProperties.class)
public class CustomDatasourceAutoconfiguration {

	@Bean
    public DataSource dataSource(CustomAutoConfigDataSourceProperties properties) {
        
		JdbcDataSource ds = new JdbcDataSource();
        
        ds.setUrl(properties.getUrl());
        ds.setUser(properties.getUsername());
        ds.setPassword(properties.getPassword());
              
        return ds;
    }
}
