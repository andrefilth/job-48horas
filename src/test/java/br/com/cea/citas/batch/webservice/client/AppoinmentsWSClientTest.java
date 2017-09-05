package br.com.cea.citas.batch.webservice.client;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.Test;

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
public class AppoinmentsWSClientTest {

	@Test
	public void testaServicoAppointmentSucesso() throws DatatypeConfigurationException {
		
		ObjectFactory factory = new ObjectFactory();
		GregorianCalendar c = new GregorianCalendar();
		AppointmentFromDB2RequestType cita = factory.createAppointmentFromDB2RequestType();

		cita.setDcDestID("gero et");
		cita.setAppointmentNumber(new BigInteger("100"));
		c.setTime(new Date());
		cita.setCreationTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		cita.setAppointmentStartTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		cita.setEstimatedDuration(new BigInteger("100"));
		cita.setAppointmentEndTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		cita.setTrailerID("regemque dedit");
		cita.setDeliveryMode("fremunt celsa");
		cita.setSealNumber("ferant rapidi");
		cita.setDoorID("sceptra tenens");
		cita.setAppointmentStatus("turbine corripuit scopuloque");
		cita.setUserID("flammato secum dea");
		cita.setCarrierCode("profundum quippe ferant");
		cita.setASNFlag(true);
		cita.setTransshipmentFlag(false);
		cita.setTransshipmentNumber(false);
		cita.setFlagQuarantineXDock(false);
		cita.setFlagPerformQA(false);
		cita.setFlagPerformVA(false);
		cita.setFlagNSC(false);
		cita.setAssetConfirm("speluncis abdidi");
		cita.setLoadType("aris imponet honorem");
		cita.setLabeledReceiving(true);
		cita.setScheduledNumber(new BigInteger("100"));

		
		AppoimentDetailType detailType = factory.createAppoimentDetailType();
		detailType.setAppointmentLine(new BigInteger("100"));
		detailType.setPONumber(new BigInteger("100"));
		detailType.setItemID("arce sceptra");
		detailType.setCasepack(new BigInteger("100"));
		detailType.setAppointedCotainerQty(new BigInteger("100"));
		detailType.setUnloadHours(new BigInteger("100"));
		detailType.setBulkFlag(false);
		detailType.setAppointedUnitQty(new BigInteger("100"));
		detailType.setDetailSource("imperio premit");
		detailType.setFlagQAFRP(false);
		detailType.setFlagVAFRP(false);
		List<AppoimentDetailType> details = new ArrayList<AppoimentDetailType>();
		details.add(detailType);

		AppointmentFromDB2BindingQSService service = new AppointmentFromDB2BindingQSService();

		AppointmentPortType port = service.getAppointmentFromDB2BindingQSPort();

		AppointmentFromDB2Response response = null;
		try {
			response = port.createAppointment(cita);
			port.modifyAppointment(cita);
			log.info("Resposta do web service : " + response.getDescription() + response.getId());
		} catch (AppointmentFromDB2FaultMsg | TechnicalFaultMsg e1) {

		}
		assertEquals("Sucesso", response.getDescription() );
	}

}
