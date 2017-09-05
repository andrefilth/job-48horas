package br.com.cea.transporte.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cea.transporte.batch.batch.service.CitasService;
import br.com.cea.transporte.batch.model.Cita;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.webservice.client.Appoinments;
import lombok.extern.log4j.Log4j;

/**
 * Created by luan on 16/05/17.
 */
@Log4j
public class CitasWriter implements ItemWriter<Cita> {
	
	@Autowired
	private Appoinments app;
	
	@Autowired
	private CitasService service;
	

//	@PostConstruct
//	public void load() throws Exception {
////		log.debug(message);
//		log.info("Iniciando job 48 horas - Buscar parametro de range das datas");
//		
//		CitaEntregaModel model = new CitaEntregaModel();
//		model.setId(2L);
//		model.setTipoItemCita("NOS");
//		model.setCodigoPedido(123);
//		cita.adiciona(model);
//		model.setId(2L);
//		model.setTipoItemCita("NOS");
//		model.setCodigoPedido(123);
//		cita.adiciona(model);
//		
//		this.app.comsumirServico(cita);


	@Override
	public void write(List<? extends Cita> items) throws Exception {
		
		log.info("Processando CitasWriter.........");
		items.forEach(cita->{
			List<CitaEntregaModel> citas = cita.getCitas();
			this.service.alterandoStatusCitasFatura(citas);
			this.app.comsumirServico(cita);
		});
		
	}

	
}
