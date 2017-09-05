package br.com.cea.transporte.batch.repository;

import java.sql.SQLException;
import java.util.List;

import br.com.cea.transporte.batch.model.CitaEntregaModel;

public interface FaturaRepository {

	void atualizar(List<CitaEntregaModel> list) throws SQLException;

	void inserir(List<CitaEntregaModel> list) throws SQLException;

}
