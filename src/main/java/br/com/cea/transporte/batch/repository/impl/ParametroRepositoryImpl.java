package br.com.cea.transporte.batch.repository.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cea.transporte.batch.model.ParametroModel;
import br.com.cea.transporte.batch.repository.ParametroRepository;
import br.com.cea.transporte.batch.repository.QuerysRepository;

@Repository
public class ParametroRepositoryImpl implements ParametroRepository {

	private static Logger logger = Logger.getLogger(ParametroRepositoryImpl.class);

	@Autowired
	private JdbcTemplate template;

	@Override
	public ParametroModel consultar(String parametro) {

		ParametroModel dadosParametro = this.template.queryForObject(QuerysRepository.CONSULTAR_PARAMETRO_JOB_ID,
				new BeanPropertyRowMapper<>(ParametroModel.class), parametro);
		logger.info("Informações de : " + dadosParametro);
		return dadosParametro;

	}

}
