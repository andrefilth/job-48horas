package br.com.cea.transporte.batch.webservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import br.com.cea.transporte.batch.webservice.client.AppoinmentsWSClient;
import lombok.extern.log4j.Log4j;

@Configuration
@Log4j
public class ClientWSConfiguration  {

	@Value("${cea.osb.metodo.uri}")
	private String integrationAppoinments;


	@Bean
	public AppoinmentsWSClient alterarCitas() {
		log.info("Mapeamento do serviço Appointments: " + integrationAppoinments);
		return (AppoinmentsWSClient) inicializaStubBean(new AppoinmentsWSClient(), integrationAppoinments);
	}
	
	private WebServiceGatewaySupport inicializaStubBean(WebServiceGatewaySupport client, String uri) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("mx.com.cea.appointments.crud.v1");
		client.setDefaultUri(uri);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	
	
	
}
