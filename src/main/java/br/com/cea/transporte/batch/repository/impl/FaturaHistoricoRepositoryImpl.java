package br.com.cea.transporte.batch.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.FaturaHistoricoRepository;
import br.com.cea.transporte.batch.repository.QuerysRepository;
import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class FaturaHistoricoRepositoryImpl implements FaturaHistoricoRepository {


	@Autowired
	private JdbcTemplate template;

	private Connection connection;
	
	@Override
	public void inserir(List<CitaEntregaModel> list) throws SQLException {

		this.connection = null;
		this.connection = this.template.getDataSource().getConnection();
		CallableStatement call = this.connection.prepareCall(QuerysRepository.INSERIR_HISTORICO_FATURA);
		list.forEach(listas -> {
			try {
				call.setLong(1, listas.getId());
				call.addBatch();
			} catch (SQLException e) {
				String msg = "Falha na inserir registros do lote de inserção para a tabela de histórico.";
				log.error(msg);
				throw new RuntimeException(msg);
			}
		});
		call.executeBatch();
		call.close();
	
		
	}

}
