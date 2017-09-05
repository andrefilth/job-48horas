package br.com.cea.transporte.batch.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cea.transporte.batch.business.CitasBusiness;
import br.com.cea.transporte.batch.model.Cita;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import lombok.extern.log4j.Log4j;

/**
 * Created by luan on 16/05/17.
 */
@Log4j
public class CitasProcessor implements ItemProcessor<Cita, Cita> {

	@Autowired
	private CitasBusiness validaCitas;

	private List<CitaEntregaModel> alterarStatus;

	@Override
	public Cita process(Cita item) throws Exception {
		this.alterarStatus = new ArrayList<>();
		log.info("Iniciando processamento das citas....");
		System.out.println("Passa aqui");
		if (item.getCitas().size() > 0) {
			alterarStatus = this.validaCitas.alterarStatus(item.getCitas());
		}

		return new Cita(alterarStatus);
		// return null;
	}

}
