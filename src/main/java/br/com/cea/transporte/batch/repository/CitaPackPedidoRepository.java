package br.com.cea.transporte.batch.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import br.com.cea.transporte.batch.model.CitaPackPedidoModel;

public interface CitaPackPedidoRepository {
	
	public List<CitaPackPedidoModel> consultar(MapSqlParameterSource params);

}
