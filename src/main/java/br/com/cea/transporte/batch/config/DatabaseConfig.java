package br.com.cea.transporte.batch.config;
//package br.com.cea.citas.batch.config;
//
//import java.sql.Connection;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class DatabaseConfig {
//	
//	private static Logger logger = Logger.getLogger(DatabaseConfig.class);
//
//	@Autowired
//	private Environment env;
//
//	
//	public DataSource dataSource() {
//
//		BasicDataSource dataSource = new BasicDataSource();
//		  
//		  dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
//		  dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
//		  dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
//		  dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
//		  dataSource.setValidationQuery("SELECT current date FROM sysibm.sysdummy1");
//		  dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//		  logger.info("Banco de dados DB2 conectado com sucesso !!! ");
//		  
//		  return dataSource;
//	}
//	
//	
//}
