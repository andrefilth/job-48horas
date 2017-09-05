package br.com.cea.transporte.batch.webservice.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import br.com.cea.transporte.batch.batch.service.CitaPedidoService;
import br.com.cea.transporte.batch.model.Cita;
import br.com.cea.transporte.batch.model.CitaPackPedidoModel;
import br.com.cea.transporte.batch.model.CitaSkuPedidoModel;
import br.com.cea.transporte.batch.utils.DatasUtils;
import lombok.extern.log4j.Log4j;
import mx.com.cea.appointments.crud.v1.AppoimentDetailType;
import mx.com.cea.appointments.crud.v1.AppointmentFromDB2BindingQSService;
import mx.com.cea.appointments.crud.v1.AppointmentFromDB2FaultMsg;
import mx.com.cea.appointments.crud.v1.AppointmentFromDB2RequestType;
import mx.com.cea.appointments.crud.v1.AppointmentFromDB2Response;
import mx.com.cea.appointments.crud.v1.AppointmentPortType;
import mx.com.cea.appointments.crud.v1.ObjectFactory;
import mx.com.cea.appointments.crud.v1.TechnicalFaultMsg;

@Log4j
public class AppoinmentsWSClient extends WebServiceGatewaySupport implements Appoinments {

	private static final String X = "X";

	private static final String DURING = "DURING";

	private static final String RD = "RD";

	private static final String GUI = "GUI";

	 @Value("${cea.osb.metodo.uri}")
	 private String integrationAppoinments;

	@Autowired
	private CitaPedidoService service;

	@Transient
	private String numeroPack;

	@Override
	public void comsumirServico(Cita citas) {

		citas.getCitas().forEach(cita -> {

			ObjectFactory factory = new ObjectFactory();
			AppointmentFromDB2RequestType request = factory.createAppointmentFromDB2RequestType();

			request.setDcDestID(String.valueOf(cita.getCodigoLocalEntrega()));
			request.setAppointmentNumber(new BigInteger(String.valueOf(cita.getCodigoPedido())));

			request.setCreationTimestamp(DatasUtils.converterXMLGregorianCalendar(cita.getDataCriacao()));

			request.setAppointmentStartTimestamp(
					DatasUtils.converterXMLGregorianCalendar(cita.getDataPrimeiraProgramacao()));

			request.setUserID(cita.getCodigoUsuario());
			request.setDoorID(String.valueOf(cita.getCodigoRampa()));
			request.setAppointmentStatus(String.valueOf(cita.getStatus()));

			request.setAppointmentEndTimestamp(DatasUtils.converterXMLGregorianCalendar(cita.getDataFinalizacao()));

			request.setTrailerID(null);
			request.setDeliveryMode(X);
			request.setSealNumber(null);
			request.setCarrierCode(null);
			request.setASNFlag(true);
			request.setTransshipmentFlag(false);
			request.setTransshipmentNumber(false);
			request.setFlagQuarantineXDock(false);
			request.setFlagPerformQA(false);
			request.setFlagPerformVA(false);
			request.setFlagNSC(false);
			request.setAssetConfirm(DURING);
			request.setLoadType(RD);
			request.setLabeledReceiving(true);
			request.setScheduledNumber(null);

			List<AppoimentDetailType> details = new ArrayList<AppoimentDetailType>();
			this.montar(cita.getTipoItemCita(), cita.getCodigoPedido(), cita.getId(), details);

			AppointmentFromDB2BindingQSService service = new AppointmentFromDB2BindingQSService();

			AppointmentFromDB2Response response = invocarServicoModificacao(request, service);
			log.info("situação da chamado do serviço: " + "Código: " + response.getId() + "\" + Status: "
					+ response.getDescription());

		});
		;

	}

	private AppointmentFromDB2Response invocarServicoModificacao(AppointmentFromDB2RequestType request,
			AppointmentFromDB2BindingQSService service) {
		AppointmentPortType port = service.getAppointmentFromDB2BindingQSPort();
		try {
			AppointmentFromDB2Response modifyAppointment = port.modifyAppointment(request);
			return modifyAppointment;
		} catch (AppointmentFromDB2FaultMsg | TechnicalFaultMsg e) {
			log.error("Falha ao acessar o serviço Appointments" + e.getMessage());
			throw new RuntimeException("Falha ao acessar o serviço Appointments");
		}
	}

	private void montar(String tipoItemCita, Integer codigoPedido, Long id, List<AppoimentDetailType> details) {
		if (tipoItemCita.equals("NOS")) {
			List<CitaSkuPedidoModel> pedido = this.service.consultarPedidoSku(codigoPedido, id);
			popularTypeSku(pedido, details);
		} else if (tipoItemCita.equals("PACK")) {
			List<CitaPackPedidoModel> pedido = this.service.consultarPedidoPack(codigoPedido, id);
			popularTypePack(pedido, details);
		} else if (tipoItemCita.equals("PACK_NOS")) {
			this.service.consultarPedidoSku(codigoPedido, id);
			this.service.consultarPedidoPack(codigoPedido, id);

		}
	}

	private void popularTypePack(List<CitaPackPedidoModel> packs, List<AppoimentDetailType> details) {
		ObjectFactory factory = new ObjectFactory();
		Integer sequencial = 1;
		packs.forEach(pack -> {
			AppoimentDetailType detailType = factory.createAppoimentDetailType();
			detailType.setAppointmentLine(new BigInteger(String.valueOf(sequencial)));
			detailType.setPONumber(new BigInteger(String.valueOf(pack.getNumeroPedido())));
			detailType.setItemID(numeroPack);
			detailType.setCasepack(new BigInteger(String.valueOf(pack.getQuantidadePorCaixas())));
			detailType.setAppointedCotainerQty(new BigInteger(String.valueOf(pack.getQuantidadeCaixas())));
			detailType.setBulkFlag(Boolean.TRUE);
			detailType.setDetailSource(GUI);
			detailType.setFlagQAFRP(false);
			detailType.setFlagVAFRP(false);
			details.add(detailType);
		});
	}

	private void popularTypeSku(List<CitaSkuPedidoModel> packs, List<AppoimentDetailType> details) {
		ObjectFactory factory = new ObjectFactory();
		Integer sequencial = 1;
		packs.forEach(pack -> {
			AppoimentDetailType detailType = factory.createAppoimentDetailType();
			detailType.setAppointmentLine(new BigInteger(String.valueOf(sequencial)));
			detailType.setPONumber(new BigInteger(String.valueOf(pack.getNumeroPedido())));
			detailType.setItemID(numeroPack);
			detailType.setCasepack(new BigInteger(String.valueOf(pack.getQuantidadePorCaixas())));
			detailType.setAppointedCotainerQty(new BigInteger(String.valueOf(pack.getQuantidadeCaixas())));
			detailType.setBulkFlag(Boolean.TRUE);
			detailType.setDetailSource(GUI);
			detailType.setFlagQAFRP(false);
			detailType.setFlagVAFRP(false);
			details.add(detailType);
		});
	}

}
