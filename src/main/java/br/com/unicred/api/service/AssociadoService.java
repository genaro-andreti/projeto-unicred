package br.com.unicred.api.service;

import java.util.Optional;

import br.com.unicred.api.model.Associado;

public interface AssociadoService {
	
	Associado cadastrar(Associado ssociado);
	
	Boolean associadoCadastrado(String idAssociado);
	
	Optional<Associado> findById(String idAssociado);

}
