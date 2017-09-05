package br.com.cea.transporte.batch.batch.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.FaturaRepository;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class FaturaService {

	@Autowired
	private FaturaRepository repository;

	public void atualizarStatusFatura(List<CitaEntregaModel> list) {
		try {
			this.repository.atualizar(list);
		} catch (SQLException e) {
			String message = "Falha ao atualizar Status de Fatura";
			log.error(message + " : " + e.getMessage());
			throw new RuntimeException(message);
		}
	}

}
