package br.com.cea.transporte.batch.model;


import java.util.Date;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class CitaEntregaModel implements Persistable<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer codigoLocalEntrega;
	private Integer status;
	private Integer codigoPedido;
	private Date dataCriacao;
	private Date dataFinalizacao;
	private Integer codigoRampa;
	private Integer inspecao;
	private String nomeMotorista;
	private String numeroPlaca;
	private String codigoFornecedor;
	private Date dataMovimento;
	private String codigoUsuario;
	private Date dataPrimeiraProgramacao;
	private Date dataUltimaProgramacao;
	private String tipoItemCita;
	private String tipoOperacao;
	private String statusSourceInspection;
	private String statusMostra;
	
	private String descricaoStatus;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}


}
