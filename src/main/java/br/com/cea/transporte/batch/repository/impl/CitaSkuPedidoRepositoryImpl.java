package br.com.cea.transporte.batch.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.cea.transporte.batch.model.CitaSkuPedidoModel;
import br.com.cea.transporte.batch.repository.CitaSkuPedidoRepository;
import br.com.cea.transporte.batch.repository.QuerysRepository;
import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class CitaSkuPedidoRepositoryImpl implements CitaSkuPedidoRepository {


	@Autowired
	private NamedParameterJdbcTemplate named;
	
	@Override
	public List<CitaSkuPedidoModel> consultar(MapSqlParameterSource params) {
		List<CitaSkuPedidoModel> list = this.named.query(QuerysRepository.CONSULTAR_CITAS_SKU_PEDIDO, params,
				new BeanPropertyRowMapper<>(CitaSkuPedidoModel.class));
		log.info("Lista de SKU Pedido: " + list);
		return list;
	}

}
