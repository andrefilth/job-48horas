package br.com.cea.transporte.batch.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cea.transporte.batch.batch.service.CitasService;
import br.com.cea.transporte.batch.model.CitaEntregaHistoricoModel;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.CitaEntregaHistoricoRepository;
import br.com.cea.transporte.batch.repository.QuerysRepository;

@Repository
public class CitaEntregaHistoricoRepositoryImpl implements CitaEntregaHistoricoRepository {

	private static Logger logger = Logger.getLogger(CitasService.class);

	@Autowired
	private NamedParameterJdbcTemplate named;

	@Autowired
	private JdbcTemplate template;

	private Connection connection;

	@Override
	public List<CitaEntregaHistoricoModel> consultar(MapSqlParameterSource params) {
		List<CitaEntregaHistoricoModel> list = this.named.query(QuerysRepository.CONSULTAR_HISTORICO_CITAS, params,
				new BeanPropertyRowMapper<>(CitaEntregaHistoricoModel.class));
		logger.info("Lista de histórico: " + list);
		return list;
	}

	@Override
	public void atualizar(List<CitaEntregaHistoricoModel> list) {

		// Connection connection =
		// this.template.getDataSource().getConnection();
		// CallableStatement call =
		// connection.prepareCall(QuerysRepository.ATUALIZAR_STATUS_CITAS);
		// list.forEach(listas -> {
		// try {
		//// call.setString(1, listas.getDescricaoStatus());
		//// call.setLong(2, listas.getId());
		// call.addBatch();
		// } catch (SQLException e) {
		// logger.error("Falha ao executar atualização de banco de dados:");
		// throw new RuntimeException("Falha ao executar atualização de banco de
		// dados:");
		// }
		// });
		// call.executeBatch();
		// call.close();
		// }
	}

	@Override
	public void inserir(List<CitaEntregaModel> list) throws SQLException {
		this.connection = null;
		this.connection = this.template.getDataSource().getConnection();
		CallableStatement call = this.connection.prepareCall(QuerysRepository.INSERIR_HISTORICO_CITAS_ENTREGAS);
		list.forEach(listas -> {
			try {
				call.setLong(1, listas.getId());
				call.addBatch();
			} catch (SQLException e) {
				throw new RuntimeException("Falha na inserir registros do lote de inserção para a tabela de histórico.");
			}
		});
		call.executeBatch();
		call.close();
	}

}