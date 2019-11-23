package folhaPagamento.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import folhaPagamento.domain.Colaborador;
import folhaPagamento.domain.ColaboradorRepository;
import folhaPagamento.domain.FolhaPagamento;
import folhaPagamento.domain.FolhaPagamentoRepository;
import folhaPagamento.domain.MovimentoFolha;
import folhaPagamento.domain.MovimentoFolhaRepository;

@RestController
public class MovimentoFolhaController {
	private final MovimentoFolhaRepository repository;
	private final FolhaPagamentoRepository folhaRepository;
	private final ColaboradorRepository colaboradorRepository;

	MovimentoFolhaController(MovimentoFolhaRepository repository, FolhaPagamentoRepository folhaRepository,ColaboradorRepository colaboradorRepository){
		this.repository = repository;
		this.colaboradorRepository = colaboradorRepository;
		this.folhaRepository = folhaRepository;
	}

	@GetMapping("/movimentos")
	List<MovimentoFolha> all(){
		return repository.findAll();
	}
	
	@GetMapping("/movimentos/{id}")
	MovimentoFolha one(@PathVariable long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MovimentoFolhaNotFoundException(id));
	}

	@PostMapping("/movimentos")
	MovimentoFolha post(@RequestBody Map<String, Object> movimento) { 
		String descricao = (String)movimento.get("descricao");
		float valor = Float.parseFloat((String)movimento.get("valor"));
		String tipoMovimento = (String)movimento.get("tipoMovimento");
		long colaboradorId = Long.parseLong((String)movimento.get("colaborador"));
		long folhaId = Long.parseLong((String)movimento.get("folha"));
		FolhaPagamento folha = folhaRepository.findById(folhaId)
				.orElseThrow(() -> new FolhaPagamentoNotFoundException(folhaId));
		Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
				.orElseThrow(() -> new ColaboradorNotFoundException(colaboradorId));
		
		MovimentoFolha newMovimento = new MovimentoFolha(descricao,valor,tipoMovimento,colaborador,folha);
		repository.save(newMovimento);
		return newMovimento;
	}
	
	@PutMapping("/movimentos/{id}")
	MovimentoFolha put(@PathVariable("id") Long id, @RequestBody Map<String, Object> putMovimento) {
		MovimentoFolha movimento = repository.findById(id)
				.orElseThrow(() -> new MovimentoFolhaNotFoundException(id));
		
		String descricao = (String)putMovimento.get("descricao");
		float valor = Float.parseFloat((String)putMovimento.get("valor"));
		String tipoMovimento = (String)putMovimento.get("tipoMovimento");

		movimento.setDescricao(descricao);
		movimento.setValor(valor);
		movimento.setTipoMovimento(tipoMovimento);
		
		repository.save(movimento);
		
		return movimento;
	}
	
	@DeleteMapping("/movimentos/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
