package folhaPagamento.web;

class ColaboradorNotFoundException extends RuntimeException{
	ColaboradorNotFoundException(long id){
		super("Não foi encontrado o colaborador "+id);
	}
}
