package br.com.cea.transporte.batch.batch.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.FaturaHistoricoRepository;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FaturaHistoricoService {

	@Autowired
	private FaturaHistoricoRepository repository;

	public void inserirHistorico(List<CitaEntregaModel> list) {
		try {
			this.repository.inserir(list);
		} catch (SQLException e) {
			log.error("Erro ao acessar a base de dados: " + e);
		}
	}

}
