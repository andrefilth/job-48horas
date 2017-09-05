package br.com.cea.citas.batch.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cea.transporte.batch.business.CitasBusiness;
import br.com.cea.transporte.batch.model.CitaEntregaModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitasBusinessTest {

	private static final String REPROBADO = "REPROBADO";
	private static final String NO_APLICA = "NO APLICA";
	private static final String APROBADO = "APROBADO";

	@Autowired
	private CitasBusiness business;

	@Test
	public void verificaSeUmaCitaFoiCancelada() {
		CitaEntregaModel citasModel = new CitaEntregaModel();
		citasModel.setStatusSourceInspection(REPROBADO);
		citasModel.setStatusMostra(APROBADO);
		citasModel.setTipoOperacao(APROBADO);
		List<CitaEntregaModel> consultarCitas = new ArrayList<>();
		consultarCitas.add(citasModel);
		List<CitaEntregaModel> alterarStatus = this.business.alterarStatus(consultarCitas);
		for (CitaEntregaModel citasModel2 : alterarStatus) {
			assertEquals("CANCELADO", citasModel2.getDescricaoStatus());
		}
	}

	@Test
	public void verificaSeUmaCitaFoiProgramada() {
		CitaEntregaModel citasModel = new CitaEntregaModel();
		citasModel.setStatusSourceInspection(APROBADO);
		citasModel.setStatusMostra(APROBADO);
		citasModel.setTipoOperacao(APROBADO);
		List<CitaEntregaModel> consultarCitas = new ArrayList<>();
		consultarCitas.add(citasModel);
		List<CitaEntregaModel> alterarStatus = this.business.alterarStatus(consultarCitas);
		for (CitaEntregaModel citasModel2 : alterarStatus) {
			assertNotEquals("CANCELADO", citasModel2.getDescricaoStatus());
		}
	}

	@Test
	public void verificaSeUmaCitaFoiProgramadaSemSourceInspection() {
		CitaEntregaModel citasModel = new CitaEntregaModel();
		citasModel.setStatusSourceInspection(NO_APLICA);
		citasModel.setStatusMostra(APROBADO);
		citasModel.setTipoOperacao(APROBADO);
		List<CitaEntregaModel> consultarCitas = new ArrayList<>();
		consultarCitas.add(citasModel);
		List<CitaEntregaModel> alterarStatus = this.business.alterarStatus(consultarCitas);
		for (CitaEntregaModel citasModel2 : alterarStatus) {
			assertNotEquals("CANCELADO", citasModel2.getDescricaoStatus());
		}
	}

	@Test
	public void verificaSeUmaCitaFoiCanceladaSemAprovacaoFatura() {
		CitaEntregaModel citasModel = new CitaEntregaModel();
		citasModel.setStatusSourceInspection(APROBADO);
		citasModel.setStatusMostra(APROBADO);
		citasModel.setTipoOperacao(REPROBADO);
		List<CitaEntregaModel> consultarCitas = new ArrayList<>();
		consultarCitas.add(citasModel);
		List<CitaEntregaModel> alterarStatus = this.business.alterarStatus(consultarCitas);
		for (CitaEntregaModel citasModel2 : alterarStatus) {
			assertEquals("CANCELADO", citasModel2.getDescricaoStatus());
		}
	}
}
