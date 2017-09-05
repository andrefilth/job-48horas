package br.com.cea.transporte.batch.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import br.com.cea.transporte.batch.model.CitaEntregaModel;

@Component
public class SqlParameterMap {

	private static final String DATA_INICIAL = "data_inicial";

	private static final String DATA_FINAL = "data_final";

	private static final String LISTA_CITAS = "lista_citas";

	private static final Integer ZERO = 0;

	private static final String CODIGO_PEDIDO = "codigo_pedido";

	private static final String ID = "id";

	private List<Integer> listaCitas = new ArrayList<>();

	public MapSqlParameterSource sqlParameter(Integer horasJob) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue(DATA_INICIAL, ZERO);
		param.addValue(DATA_FINAL, horasJob);
		return param;

	};

	public MapSqlParameterSource sqlParameter(List<CitaEntregaModel> list) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		list.forEach(citas -> listaCitas.add(citas.getCodigoPedido()));
		param.addValue(LISTA_CITAS, listaCitas);
		return param;
	}

	public MapSqlParameterSource sqlParameter(Integer codigoPedido, Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue(CODIGO_PEDIDO, codigoPedido);
		param.addValue(ID, id);
		return param;
	}

}
