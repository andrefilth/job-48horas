package br.com.cea.transporte.batch.type;

public enum StatusType {

	APROBADO("APROBADO"), NO_APLICA("NO APLICA"),REPROBADO("REPROBADO");                            
	
	private String descricao;

	private StatusType(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static String retornaCodigo(StatusType preProgramado){
		if (!(preProgramado == null)) {
			for (StatusType type : StatusType.values()) {
				if (type.equals(preProgramado)) {
					return type.getDescricao();
				}
			}
		}
		throw new NullPointerException("O código do Parâmetro não pode ser nulo");
	}	
}
