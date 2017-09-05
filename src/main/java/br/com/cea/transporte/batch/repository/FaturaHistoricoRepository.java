package br.com.cea.transporte.batch.repository;

import java.sql.SQLException;
import java.util.List;

import br.com.cea.transporte.batch.model.CitaEntregaModel;

public interface FaturaHistoricoRepository {

	void inserir(List<CitaEntregaModel> list) throws SQLException;
}
