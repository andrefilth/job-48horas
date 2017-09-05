package br.com.cea.citas.batch.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cea.transporte.batch.batch.service.CitasService;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.repository.CitaEntregaRepository;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
public class CitaEntregaRepositoryImplTest extends CitasService {
	
	@Autowired
	private CitaEntregaRepository repository;

	@Test
	public void testaAtualizacaoCitaEntregaNaBase() {
		List<CitaEntregaModel> list = new ArrayList<>();
		CitaEntregaModel cita = new CitaEntregaModel();
		CitaEntregaModel cita2 = new CitaEntregaModel();
		cita.setId(86L);
		cita.setStatus(4);;
		list.add(cita);
		cita2.setId(87L);
		cita2.setStatus(5);
		list.add(cita2);
		

		try {
			this.repository.atualizar(list);
		} catch (SQLException e) {
			
		}
		
		
	}

}
