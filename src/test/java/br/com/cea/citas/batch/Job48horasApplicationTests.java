package br.com.cea.citas.batch;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Job48horasApplicationTests {

	private static Logger logger = Logger.getLogger(Job48horasApplicationTests.class);

	@Autowired
	JdbcTemplate template;

	@Test
	public void contextLoads() {
		logger.info("Iniciando teste....");
	}

	@Test
	public void testaConexaoComBancoDB2() throws SQLException {
		Connection dataSource = template.getDataSource().getConnection();
		assertNotNull(dataSource);
	}

}
