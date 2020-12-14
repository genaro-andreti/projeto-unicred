package br.com.unicred.api.service;

import java.util.List;
import java.util.Optional;

import br.com.unicred.api.model.SessaoVotacao;

public interface SessaoVotacaoService {
	
	SessaoVotacao cadastrar(SessaoVotacao sessaoVotacao);
	
	Optional<SessaoVotacao> sessaoVotacaoCadastrado(String idSessaoVotacao);
	
	SessaoVotacao getByPauta(String idPauta);
	
	List<SessaoVotacao> findAll();

}
