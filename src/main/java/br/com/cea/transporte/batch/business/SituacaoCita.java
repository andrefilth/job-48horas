package br.com.cea.transporte.batch.business;

import br.com.cea.transporte.batch.model.CitaEntregaModel;

public interface SituacaoCita {

	boolean validaStatus(CitaEntregaModel cita);
}
