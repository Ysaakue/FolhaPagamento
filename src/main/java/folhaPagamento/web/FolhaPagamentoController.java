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

import folhaPagamento.domain.FolhaPagamento;
import folhaPagamento.domain.FolhaPagamentoRepository;

@RestController
public class FolhaPagamentoController {
	private final FolhaPagamentoRepository repository;

	FolhaPagamentoController(FolhaPagamentoRepository repository){
		this.repository = repository;
	}

	@GetMapping("/folhas")
	List<FolhaPagamento> all(){
		return repository.findAll();
	}
	
	@GetMapping("/folhas/{id}")
	FolhaPagamento one(@PathVariable long id) {
		return repository.findById(id)
				.orElseThrow(() -> new FolhaPagamentoNotFoundException(id));
	}
	
	@PostMapping("/folhas")
	FolhaPagamento post(@RequestBody Map<String, Object> folha) {
		int mes = (int)folha.get("mes");
		int ano = (int)folha.get("ano");
		
		FolhaPagamento newFolhaPagamento = new FolhaPagamento(mes,ano);
		repository.save(newFolhaPagamento);
		return newFolhaPagamento;
	}
	
	@PutMapping("/folhas/{id}")
	FolhaPagamento put(@PathVariable("id") Long id, @RequestBody Map<String, Object> putFolha) {
		FolhaPagamento folha = repository.findById(id)
				.orElseThrow(() -> new FolhaPagamentoNotFoundException(id));
		
		int mes = (int)putFolha.get("mes");
		int ano = (int)putFolha.get("ano");
		
		folha.setMes(mes);
		folha.setAno(ano);
		
		repository.save(folha);
		
		return folha;
	}
	
	@DeleteMapping("/folhas/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}