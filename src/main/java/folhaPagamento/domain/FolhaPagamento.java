package folhaPagamento.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class FolhaPagamento {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int mes,ano;
	private float totalDesconto,totalProventos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="folha")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<Colaborador> colaboradores = new LinkedList<Colaborador>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="folha")
	@JsonManagedReference
	private List<MovimentoFolha> movimentos = new LinkedList<MovimentoFolha>();

	FolhaPagamento () {}
	
	public FolhaPagamento(int mes, int ano) {
		super();
		this.mes = mes;
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public float getTotalDescontos() {
		this.totalDesconto = 0;
		
		if(this.movimentos != null) {
			for(MovimentoFolha movimento: this.movimentos){
		      if(movimento.getTipoMovimento() == TipoMovimento.DESCONTO){
		    	  this.totalDesconto += movimento.getValor();
		      }
		    }
		}
		
		return this.totalDesconto;
	}
	
	public float getTotalProventos() {
		this.totalProventos = 0;
		
		if(this.movimentos != null) {
			for(MovimentoFolha movimento: this.movimentos){
		      if(movimento.getTipoMovimento() == TipoMovimento.PROVENTO){
		    	  this.totalProventos += movimento.getValor();
		      }
		    }
		}
			
		return this.totalProventos;
	}

	public long getId() {
		return id;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<MovimentoFolha> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<MovimentoFolha> movimentos) {
		this.movimentos = movimentos;
	}
	
	
}
