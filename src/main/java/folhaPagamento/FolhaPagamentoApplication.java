package folhaPagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import folhaPagamento.domain.Colaborador;
import folhaPagamento.domain.ColaboradorRepository;
import folhaPagamento.domain.FolhaPagamento;
import folhaPagamento.domain.FolhaPagamentoRepository;
import folhaPagamento.domain.MovimentoFolha;
import folhaPagamento.domain.MovimentoFolhaRepository;
import folhaPagamento.domain.TipoMovimento;

@SpringBootApplication
public class FolhaPagamentoApplication {
	@Autowired
	private FolhaPagamentoRepository Frepository;
	@Autowired
	private ColaboradorRepository Crepository;
	@Autowired
	private MovimentoFolhaRepository Mrepository;

	public static void main(String[] args) {
		SpringApplication.run(FolhaPagamentoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Save demo data to database
			
			FolhaPagamento f1 = new FolhaPagamento(11, 2019);
			Frepository.save(f1);
			
			Colaborador colab1 = new Colaborador("João", "Rua X", "(85) 98824-2372", "Lagoa Redonda","11111-11", "xxx.xxx.xxx-xx", (double) 1200,f1);
			Crepository.save(colab1);
			Colaborador colab2 = new Colaborador("Mauricio", "Rua Y", "(85) 98212-2121", "Caucaia","11111-11", "xxx.xxx.xxx-xx", (double) 800,f1);
			Crepository.save(colab2);
			Colaborador colab3 = new Colaborador("Alerrandro", "Rua Z", "(85) 99228-0921", "Serrinha","11111-11", "xxx.xxx.xxx-xx", (double) 600,f1);
			Crepository.save(colab3);
			
			MovimentoFolha m1 = new MovimentoFolha("Falta", (float)120, TipoMovimento.DESCONTO, colab1, f1);
			Mrepository.save(m1);
			MovimentoFolha m2 = new MovimentoFolha("Horas extras", (float) 1200, "P",colab1,f1);
			Mrepository.save(m2);
			MovimentoFolha m3 = new MovimentoFolha("Inprodução", (float)70, TipoMovimento.PROVENTO, colab2, f1);
			Mrepository.save(m3);
			MovimentoFolha m4 = new MovimentoFolha("Décimo Terceiro", (float) 1350, "D",colab2,f1);
			Mrepository.save(m4);
			MovimentoFolha m5 = new MovimentoFolha("Faltas", (float)80, TipoMovimento.PROVENTO, colab3, f1);
			Mrepository.save(m5);
			MovimentoFolha m6 = new MovimentoFolha("Férias", (float) 300, TipoMovimento.DESCONTO,colab3,f1);
			Mrepository.save(m6);
		};
	}

}
