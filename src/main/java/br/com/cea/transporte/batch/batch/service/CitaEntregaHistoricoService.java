package br.com.cea.transporte.batch.batch.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import br.com.cea.transporte.batch.map.SqlParameterMap;
import br.com.cea.transporte.batch.model.CitaEntregaHistoricoModel;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.CitaEntregaHistoricoRepository;

@Service
public class CitaEntregaHistoricoService {

	@Autowired
	private CitaEntregaHistoricoRepository repository;

	@Autowired
	private SqlParameterMap map;

	public List<CitaEntregaHistoricoModel> consultarHistorico(List<CitaEntregaModel> list) {
		MapSqlParameterSource sqlParameter = this.map.sqlParameter(list);
		return this.repository.consultar(sqlParameter);

	}

	public void inserirHistorico(List<CitaEntregaModel> citasHistoricas) {
		try {
			this.repository.inserir(citasHistoricas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void atualizarHistorico(List<CitaEntregaHistoricoModel>
	// citasHistoricas) {
	// try {
	// this.repository.atualizar(citasHistoricas);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

}
