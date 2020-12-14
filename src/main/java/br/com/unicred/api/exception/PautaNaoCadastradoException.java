package br.com.unicred.api.exception;

public class PautaNaoCadastradoException extends VotacaoRuntimeException {
	private static final long serialVersionUID = -4584334873570981856L;
	
	private static final String msgPautaNaoCadastrada = "Pauta não cadastrada.";

	public PautaNaoCadastradoException() {
		super(msgPautaNaoCadastrada);
	}

}
