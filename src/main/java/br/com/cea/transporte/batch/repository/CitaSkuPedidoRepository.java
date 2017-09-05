package br.com.cea.transporte.batch.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import br.com.cea.transporte.batch.model.CitaSkuPedidoModel;

public interface CitaSkuPedidoRepository {

	public List<CitaSkuPedidoModel> consultar(MapSqlParameterSource params);
	
}
