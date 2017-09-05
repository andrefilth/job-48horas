package br.com.cea.transporte.batch.batch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import br.com.cea.transporte.batch.map.SqlParameterMap;
import br.com.cea.transporte.batch.model.CitaPackPedidoModel;
import br.com.cea.transporte.batch.model.CitaSkuPedidoModel;
import br.com.cea.transporte.batch.repository.CitaPackPedidoRepository;
import br.com.cea.transporte.batch.repository.CitaSkuPedidoRepository;

@Service
public class CitaPedidoService {

	@Autowired
	private CitaSkuPedidoRepository repositorySku;

	@Autowired
	private CitaPackPedidoRepository repositoryPack;

	@Autowired
	private SqlParameterMap map;

	public List<CitaSkuPedidoModel> consultarPedidoSku(Integer codigoPedido, Long id) {
		MapSqlParameterSource sqlParameter = this.map.sqlParameter(codigoPedido, id);
		return this.repositorySku.consultar(sqlParameter);

	}

	public List<CitaPackPedidoModel> consultarPedidoPack(Integer codigoPedido, Long id) {
		MapSqlParameterSource sqlParameter = this.map.sqlParameter(codigoPedido, id);
		return this.repositoryPack.consultar(sqlParameter);
	}

}
