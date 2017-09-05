package br.com.cea.transporte.batch.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Log
public class Cita {

	private List<CitaEntregaModel> citas = new ArrayList<>();

	public void adiciona(CitaEntregaModel model) {
		log.info("Adicionando uma cita: " + model);
		citas.add(model);
	}
}
