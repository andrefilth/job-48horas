package br.com.cea.transporte.batch.type;


public enum ParametroType  {

	JOB_48("CT_HORAS_JOB_48HORAS");

	private String descricao;

	private ParametroType(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}

//	public static String retornaDescricaoPorCodigo(Integer codigo) {
//		if (!(codigo == null)) {
//			for (ParametroType type : ParametroType.values()) {
//				if (type.codigo.equals(codigo)) {
//					return type.getDescricao();
//				}
//			}
//		}
//		throw new NullPointerException("O código do Parâmetro não pode ser nulo");
//	}
}