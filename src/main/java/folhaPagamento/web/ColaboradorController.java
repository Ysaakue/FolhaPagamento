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

@RestController
public class ColaboradorController {
	private final ColaboradorRepository repository;
	private final FolhaPagamentoRepository folhaRepository;
	
	ColaboradorController(ColaboradorRepository repository, FolhaPagamentoRepository folhaRepository){
		this.repository = repository;
		this.folhaRepository = folhaRepository;
	}
	
	@GetMapping("/colaboradores")
	List<Colaborador> all(){
		return repository.findAll();
	}
	
	@GetMapping("/colaboradores/{id}")
	Colaborador one(@PathVariable long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ColaboradorNotFoundException(id));
	}
	
	@PostMapping("/colaboradores")
	Colaborador post(@RequestBody Map<String, Object> colaborador) {
		String nome = (String)colaborador.get("nome");
		String endereco = (String)colaborador.get("endereco");
		String telefone = (String)colaborador.get("telefone");
		String bairro = (String)colaborador.get("bairro");
		String cep = (String)colaborador.get("cep");
		String cpf = (String)colaborador.get("cpf");
		double salario = (double)colaborador.get("salarioAtual");
		long folhaId = Long.parseLong((String)colaborador.get("folha"));
		FolhaPagamento folha = folhaRepository.findById(folhaId)
				.orElseThrow(() -> new FolhaPagamentoNotFoundException(folhaId));
		
		Colaborador newColaborador = new Colaborador(nome, endereco, telefone, bairro, cep, cpf, salario, folha);
		repository.save(newColaborador);
		return newColaborador;
	}
	
	@PutMapping("/colaboradores/{id}")
	Colaborador put(@PathVariable("id") Long id, @RequestBody Map<String, Object> putColaborador) {
		Colaborador colaborador = repository.findById(id)
				.orElseThrow(() -> new ColaboradorNotFoundException(id));
		String nome = (String)putColaborador.get("nome");
		String endereco = (String)putColaborador.get("endereco");
		String telefone = (String)putColaborador.get("telefone");
		String bairro = (String)putColaborador.get("bairro");
		String cep = (String)putColaborador.get("cep");
		String cpf = (String)putColaborador.get("cpf");
		Double salario = (Double)putColaborador.get("salarioAtual");
		
		colaborador.setNome(nome);
		colaborador.setEndereco(endereco);
		colaborador.setTelefone(telefone);
		colaborador.setBairro(bairro);
		colaborador.setCep(cep);
		colaborador.setCpf(cpf);
		colaborador.setSalarioAtual(salario);
		
		repository.save(colaborador);
		
		return colaborador;
	}
	
	@DeleteMapping("/colaboradores/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
