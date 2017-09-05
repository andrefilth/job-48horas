package br.com.cea.transporte.batch.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import br.com.cea.transporte.batch.model.CitaEntregaModel;

public interface CitaEntregaRepository {
	
	List<CitaEntregaModel> consultar(MapSqlParameterSource params);
	
	void atualizar(List<CitaEntregaModel> list) throws SQLException;
	
	
	

}
