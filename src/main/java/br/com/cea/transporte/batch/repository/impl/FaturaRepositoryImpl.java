package br.com.cea.transporte.batch.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.FaturaRepository;
import br.com.cea.transporte.batch.repository.QuerysRepository;
import br.com.cea.transporte.batch.type.UsuarioType;
import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class FaturaRepositoryImpl implements FaturaRepository {

	@Autowired
	private JdbcTemplate template;

	@Override
	public void atualizar(List<CitaEntregaModel> list) throws SQLException {

		Connection connection = this.template.getDataSource().getConnection();
		CallableStatement call = connection.prepareCall(QuerysRepository.ATUALIZAR_STATUS_FATURA);
		list.forEach(listas -> {
			{
				if (listas.getDescricaoStatus().equals("PROGRAMADO")) {

					try {
						call.setInt(1, listas.getStatus());
						call.setString(2, UsuarioType.MXJB48HS.getDescricao());
						call.setLong(3, listas.getId());
					} catch (SQLException e) {
						String message = "Falha na inserir registros do lote de inserção para a tabela de histórico.";
						log.error(message);
						throw new RuntimeException();
					}
				}
			}
		});
		call.executeBatch();
		call.close();
	}

	@Override
	public void inserir(List<CitaEntregaModel> list) throws SQLException {

	}

}
