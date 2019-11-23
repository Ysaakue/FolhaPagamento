package folhaPagamento.domain;

public enum TipoMovimento {
	PROVENTO(1),DESCONTO(2);
	private int opcao;

	private TipoMovimento(int opcao) {
		this.opcao = opcao;
	}

	public int getOpcao() {
		return opcao;
	}

	public void setOpcao(int opcao) {
		this.opcao = opcao;
	}
}
