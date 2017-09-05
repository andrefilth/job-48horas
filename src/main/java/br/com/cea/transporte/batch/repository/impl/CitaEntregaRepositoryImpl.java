package br.com.cea.transporte.batch.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.CitaEntregaRepository;
import br.com.cea.transporte.batch.repository.QuerysRepository;
import br.com.cea.transporte.batch.type.UsuarioType;
import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class CitaEntregaRepositoryImpl implements CitaEntregaRepository {


	@Autowired
	private NamedParameterJdbcTemplate named;

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<CitaEntregaModel> consultar(MapSqlParameterSource params) {
		// // mock
		List<CitaEntregaModel> query = this.named.query(QuerysRepository.CONSULTAS_CITAS_FINAL, params,
				new BeanPropertyRowMapper<>(CitaEntregaModel.class));
		log.info("Lista de Citas" + query);
		return query;
	}

	@Override
	public void atualizar(List<CitaEntregaModel> list) throws SQLException {
		Connection connection = this.template.getDataSource().getConnection();
		CallableStatement call = connection.prepareCall(QuerysRepository.ATUALIZAR_STATUS_CITAS);
		list.forEach(listas -> {
			try {
				call.setInt(1, listas.getStatus());
				call.setString(2, UsuarioType.MXJB48HS.getDescricao());
				call.setLong(3, listas.getId());
				call.addBatch();
			} catch (SQLException e) {
				log.error("Falha ao executar atualização de banco de dados:" + e);
				throw new RuntimeException("Falha ao executar atualização de banco de dados:");
			}
			
		});
		call.executeBatch();
		call.close();
	}

	

}
