package br.com.cea.transporte.batch.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import br.com.cea.transporte.batch.model.CitaEntregaHistoricoModel;
import br.com.cea.transporte.batch.model.CitaEntregaModel;


public interface CitaEntregaHistoricoRepository {

	List<CitaEntregaHistoricoModel> consultar(MapSqlParameterSource params);

	void atualizar(List<CitaEntregaHistoricoModel> list);
	
	void inserir(List<CitaEntregaModel> list) throws SQLException;
	
}
