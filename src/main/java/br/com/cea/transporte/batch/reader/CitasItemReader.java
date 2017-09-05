package br.com.cea.transporte.batch.reader;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cea.transporte.batch.batch.service.CitasService;
import br.com.cea.transporte.batch.batch.service.ParametroService;
import br.com.cea.transporte.batch.model.Cita;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.type.ParametroType;
import lombok.extern.log4j.Log4j;

@Log4j
public class CitasItemReader implements ItemReader<Cita> {

	private Integer rangeBuscaData;
	
	 private boolean batchJobState = false;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private CitasService citasService;


	@PostConstruct
	public void load() throws Exception {
		log.info("Iniciando job 48 horas - Buscar parametro de range das datas");
		this.rangeBuscaData = this.parametroService.consultaParametro(ParametroType.JOB_48.getDescricao());
	}

	@Override
	public Cita read() throws Exception {
		log.debug("Iniciando job 48 horas - Leitura das Citas");
		if(!batchJobState) {
			// rangeBuscaData = 4800;
			List<CitaEntregaModel> consultarCitas = this.citasService.consultarCitas(rangeBuscaData);
			batchJobState=true;
			if(consultarCitas.size() == 0){
				log.info("Não existem citas para processamento");
				return null;
			}
			return new Cita(consultarCitas);
		}
		return null;
	}

}