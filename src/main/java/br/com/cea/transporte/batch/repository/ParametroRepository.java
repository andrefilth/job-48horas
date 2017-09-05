package br.com.cea.transporte.batch.repository;

import br.com.cea.transporte.batch.model.ParametroModel;


public interface ParametroRepository  {

	ParametroModel consultar(String parametro);
}
