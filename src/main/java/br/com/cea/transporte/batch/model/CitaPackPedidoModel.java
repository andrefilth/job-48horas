package br.com.cea.transporte.batch.model;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class CitaPackPedidoModel  implements Persistable<Long> {

	private static final long serialVersionUID = 1056382467759718794L;
	
	private Long id;
	private Integer numeroPedido;
	private Integer idCita;
	private String codigoPackPedido;
	private Integer numeroSku;
	private Integer quantidadeCaixas;
	private Integer quantidadePorCaixas;
	private Integer resto;
	

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return id==null;
	}

}
