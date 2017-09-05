package br.com.cea.transporte.batch.model;

import java.util.Date;

import org.springframework.data.domain.Persistable;

import br.com.cea.transporte.batch.utils.DatasUtils;
import lombok.Data;

@Data
public class CitaEntregaHistoricoModel implements Persistable<Long>, Comparable<CitaEntregaModel> {

	private static final long serialVersionUID = -3528069598182254044L;

	private Long id;
	private Long idCitas;
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
	private Date dataCitaAtualizacao;
	private Integer codigoMotivoCancelamento;
	private Integer codigoMotivoRechado;
	private String ObservacaoCancelamento;
	private String ObservacaoRechado;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public int compareTo(CitaEntregaModel cita) {
		return this.id.compareTo(cita.getId());
	}

	public Date getDataCitaAtualizacao() {
		if (dataCitaAtualizacao == null) {
			this.dataCitaAtualizacao = DatasUtils.mostrarDataAtual();
		}
		return dataCitaAtualizacao;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}

}
