package br.com.unicred.api.service;

import br.com.unicred.api.dto.RetornoVotacaoDto;
import br.com.unicred.api.model.Voto;

public interface VotoService {

	Voto cadastrar(Voto voto);
	
	Boolean votoAssociadoCadastradoParaPauta(String idAssociado, String idPauta);
	
	RetornoVotacaoDto retornaVotacaoPorPauta(String idPauta);

}
