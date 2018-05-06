package com.martin.component;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	
	@Bean(name="primaryDS")
	@Qualifier("primaryDS")
	@ConfigurationProperties(prefix="spring.ds.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="secondaryDS")
	@Qualifier("secondaryDS")
	@ConfigurationProperties(prefix="spring.ds.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(
	        @Qualifier("primaryDS") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(
	        @Qualifier("secondaryDS") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
}
