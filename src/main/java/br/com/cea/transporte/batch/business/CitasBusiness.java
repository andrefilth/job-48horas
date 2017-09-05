package br.com.cea.transporte.batch.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cea.transporte.batch.model.CitaEntregaModel;

@Component
public class CitasBusiness {

	@Autowired
	private SituacaoCita cita;


	private Boolean status;


	public List<CitaEntregaModel> alterarStatus(List<CitaEntregaModel> consultarCitas) {
		List<CitaEntregaModel> list = new ArrayList<>();
		for (CitaEntregaModel citasModel : consultarCitas) {
			boolean validaStatus = this.cita.validaStatus(citasModel);
			CitaEntregaModel citasValidada = this.alterarStatus(validaStatus, citasModel);
			list.add(citasValidada);

		}
		return list;

	}

	private CitaEntregaModel alterarStatus(boolean validaStatusCitas, CitaEntregaModel citasModel) {
		if (validaStatusCitas) {
			citasModel.setDescricaoStatus("PROGRAMADO");
			citasModel.setStatus(4);
		} else {
			citasModel.setDescricaoStatus("CANCELADO");
			citasModel.setStatus(5);
		}
		return citasModel;
	}

	public Boolean getStatus() {
		return status;
	}
}
