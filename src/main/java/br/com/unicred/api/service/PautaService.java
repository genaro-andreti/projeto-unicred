package br.com.unicred.api.service;

import br.com.unicred.api.model.Pauta;

public interface PautaService {
	
	Pauta cadastrar(Pauta pauta);
	
	Boolean pautaCadastrada(String idPauta);

}
