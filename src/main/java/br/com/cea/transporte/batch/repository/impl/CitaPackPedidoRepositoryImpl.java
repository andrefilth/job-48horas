package br.com.cea.transporte.batch.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cea.transporte.batch.model.CitaPackPedidoModel;
import br.com.cea.transporte.batch.repository.CitaPackPedidoRepository;
import br.com.cea.transporte.batch.repository.QuerysRepository;
import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class CitaPackPedidoRepositoryImpl implements CitaPackPedidoRepository {

	@Autowired
	private NamedParameterJdbcTemplate named;
	
	@Override
	public List<CitaPackPedidoModel> consultar(MapSqlParameterSource params) {
		List<CitaPackPedidoModel> list = this.named.query(QuerysRepository.CONSULTAR_CITAS_PACK_PEDIDO, params,
				new BeanPropertyRowMapper<>(CitaPackPedidoModel.class));
		log.info("Lista de SKU Pedido: " + list);
		return list;
	}

}
