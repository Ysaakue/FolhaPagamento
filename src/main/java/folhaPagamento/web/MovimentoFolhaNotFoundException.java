package folhaPagamento.web;

class MovimentoFolhaNotFoundException extends RuntimeException{
	MovimentoFolhaNotFoundException(long id){
		super("Não foi encontrado o movimento "+id);
	}
}