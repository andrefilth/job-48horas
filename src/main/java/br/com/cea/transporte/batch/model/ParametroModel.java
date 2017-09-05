package br.com.cea.transporte.batch.model;

import java.sql.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ParametroModel implements Persistable<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String periodo;

	private Date dataCriacao;

	private Date dataModificacao;

	private String codigoUsuario;

	private String descricao;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}

	public Integer periodoJob() {
		return Integer.valueOf(StringUtils.substringBefore(this.periodo, ":"));
	}


}
