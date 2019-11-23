package folhaPagamento.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Colaborador {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nome,endereco,telefone,bairro,cep,cpf;
	private double salarioAtual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folha")
	@JsonBackReference
	private FolhaPagamento folha;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="colaborador")
	@JsonManagedReference
	private List<MovimentoFolha> movimentos = new LinkedList<MovimentoFolha>();

	Colaborador() {}
	
	public Colaborador(String nome, String endereco, String telefone, String bairro, String cep, String cpf,
			double salarioAtual, FolhaPagamento folha) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.bairro = bairro;
		this.cep = cep;
		this.cpf = cpf;
		this.salarioAtual = salarioAtual;
		this.folha = folha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSalarioAtual() {
		return salarioAtual;
	}

	public void setSalarioAtual(double salarioAtual) {
		this.salarioAtual = salarioAtual;
	}

	public FolhaPagamento getFolha() {
		return folha;
	}

	public void setFolha(FolhaPagamento folha) {
		this.folha = folha;
	}

	public List<MovimentoFolha> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<MovimentoFolha> movimentos) {
		this.movimentos = movimentos;
	}

	public long getId() {
		return id;
	}
	
	
}
