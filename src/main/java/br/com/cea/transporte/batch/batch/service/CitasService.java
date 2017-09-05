package br.com.cea.transporte.batch.batch.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import br.com.cea.transporte.batch.map.SqlParameterMap;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.CitaEntregaRepository;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CitasService {


	@Autowired
	private CitaEntregaRepository repository;

	@Autowired
	private CitaEntregaHistoricoService service;

	@Autowired
	private FaturaService faturaService ;
	
	@Autowired
	private FaturaHistoricoService faturaHist;
	
	@Autowired
	private SqlParameterMap map;

	public List<CitaEntregaModel> consultarCitas(Integer horasJob) {
		log.info("Consultas lotes para processamento");
		MapSqlParameterSource sqlParameter = this.map.sqlParameter(horasJob);
		List<CitaEntregaModel> consultar = this.repository.consultar(sqlParameter);
		log.info("Lotes capturados " + consultar);
		return consultar;
	}

	@Transactional
	public void alterandoStatusCitasFatura(List<CitaEntregaModel> list) {
		log.info("Executando atualizarCitas");
//		List<CitaEntregaHistoricoModel> citasHistoricas = this.service.consultarHistorico(list);
		try {
			this.repository.atualizar(list);
			this.faturaService.atualizarStatusFatura(list);
			this.service.inserirHistorico(list);
			this.faturaHist.inserirHistorico(list);
			log.info("Citas");
		} catch (SQLException e) {
			log.info("Erro ao atualizar os status das cita ");
		}
	}
}
