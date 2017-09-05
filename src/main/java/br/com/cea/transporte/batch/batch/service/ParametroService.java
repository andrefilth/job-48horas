package br.com.cea.transporte.batch.batch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cea.transporte.batch.repository.ParametroRepository;
import lombok.extern.log4j.Log4j;

@Service 
@Log4j
public class ParametroService  {

	@Autowired
	private ParametroRepository repository;
	

	public Integer consultaParametro(String parametro) {
		log.info("Consultar parametro do job.");
		return this.repository.consultar(parametro).periodoJob();
	}

}
