package folhaPagamento.web;

class FolhaPagamentoNotFoundException extends RuntimeException{
	FolhaPagamentoNotFoundException(long id){
		super("Não foi encontrado a folha "+id);
	}
}
