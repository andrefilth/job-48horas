package br.com.cea.transporte.batch.business;

import org.springframework.stereotype.Component;

import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.type.StatusType;

@Component
public class ValidaSituacaoCita implements SituacaoCita {

	private static final String NO_APLICA = StatusType.NO_APLICA.getDescricao();
	private static final String APROBADO = StatusType.APROBADO.getDescricao();

	@Override
	public boolean validaStatus(CitaEntregaModel cita) {
		return (cita.getStatusSourceInspection().equals(APROBADO) || cita.getStatusSourceInspection().equals(NO_APLICA))
				&& cita.getTipoOperacao().equals(APROBADO) && cita.getStatusMostra().equals(APROBADO);
	}
}
