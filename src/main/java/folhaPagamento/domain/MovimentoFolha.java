package folhaPagamento.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class MovimentoFolha {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String descricao;
	private float valor;
	private TipoMovimento tipoMovimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "colaborador")
	@JsonBackReference
	private Colaborador colaborador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folha")
	@JsonBackReference
	private FolhaPagamento folha;

	MovimentoFolha() {}
	
	public MovimentoFolha(String descricao, float valor, TipoMovimento tipoMovimento, Colaborador colaborador,
			FolhaPagamento folha) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.tipoMovimento = tipoMovimento;
		this.colaborador = colaborador;
		this.folha = folha;
	}
	
	public MovimentoFolha(String descricao, float valor, String tipoMovimento, Colaborador colaborador,
			FolhaPagamento folha) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		if(tipoMovimento.equals("P")) {
			this.tipoMovimento = TipoMovimento.PROVENTO;
		} else {
			this.tipoMovimento = TipoMovimento.DESCONTO;
		}
		this.colaborador = colaborador;
		this.folha = folha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public TipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		if(tipoMovimento.equals("P")) {
			this.tipoMovimento = TipoMovimento.PROVENTO;
		} else {
			this.tipoMovimento = TipoMovimento.DESCONTO;
		}
	}
	
	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public FolhaPagamento getFolha() {
		return folha;
	}

	public void setFolha(FolhaPagamento folha) {
		this.folha = folha;
	}
	
}
