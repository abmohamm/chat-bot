package com.app.samples.chatbot.chatbotapp.config;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class DatasourceConfiguration.
 */
@Configuration
@PropertySources(@PropertySource("classpath:application-dev.properties"))
public class DatasourceConfiguration {

	/** The logger. */
	private Logger logger = Logger.getLogger(DatasourceConfiguration.class.getName());
	
	/** The jdbc template. */
	JdbcTemplate jdbcTemplate = null;
	
	/** The class name. */
	private static String CLASS_NAME = "DatasourceConfiguration";
	
	/** The driver class name. */
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	/** The db url. */
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	/** The db username. */
	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	/** The db password. */
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	/**
	 * Creates the data source.
	 *
	 * @return the data source
	 */
	@Bean
	public DataSource createDataSource() {
		String METHOD_NAME = "createDataSource()";
		logger.info("Creating DataSource in method "+ METHOD_NAME + " " + CLASS_NAME);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	/**
	 * Creates the jdbc template.
	 *
	 * @param dataSource the data source
	 * @return the jdbc template
	 */
	@Bean
	public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		String METHOD_NAME = "createJdbcTemplate()";
		logger.info("Creating JdbcTemplate in method "+ METHOD_NAME + " " + CLASS_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;		
	}
}
