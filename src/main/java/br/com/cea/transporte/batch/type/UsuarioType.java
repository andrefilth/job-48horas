package br.com.cea.transporte.batch.type;

public enum UsuarioType {

	MXJB48HS("MXJB48HS");
	
	private String descricao;

	private UsuarioType(String descricao) {
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
